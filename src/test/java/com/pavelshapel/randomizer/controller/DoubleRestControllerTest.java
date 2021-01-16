package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.entity.RandomEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = DoubleRestController.class)
class DoubleRestControllerTest extends AbstractRestControllerTest<Double> {
    @Test
    void getRandomEntity_WithDefaultRange_ShouldReturnRandomEntity() {
        when(getRandomizer().getRandomEntity()).thenReturn(getMockRandomEntity());

        MvcResult mvcResult = getMvcResult(getMockRandomEntity());
        final RandomEntity<Double> randomEntityFromResponse = getRandomEntityFromResponse(mvcResult);
        assertThat(randomEntityFromResponse.toString()).isEqualToIgnoringCase((getMockRandomEntity().toString()));

        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());

        verify(getRandomizer(), times(1)).getRandomEntity();
    }

    @Override
    protected RandomEntity<Double> getMockRandomEntity() {
        return new RandomEntity<>(123.456, Double.class);
    }
}