package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.entity.RandomEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = BooleanRestController.class)
class BooleanRestControllerTest extends AbstractRestControllerTest<Boolean> {
    @Test
    void getRandomEntity_WithDefaultRange_ShouldReturnRandomEntity() {
        when(getRandomizer().getRandomEntity()).thenReturn(getMockRandomEntity());

        MvcResult mvcResult = getMvcResult(getMockRandomEntity());
        final RandomEntity<Boolean> randomEntityFromResponse = getRandomEntityFromResponse(mvcResult);
        assertThat(randomEntityFromResponse.toString()).isEqualToIgnoringCase((getMockRandomEntity().toString()));

        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());

        verify(getRandomizer(), times(1)).getRandomEntity();
    }

    @Override
    protected RandomEntity<Boolean> getMockRandomEntity() {
        return new RandomEntity<>(true, Boolean.class);
    }
}