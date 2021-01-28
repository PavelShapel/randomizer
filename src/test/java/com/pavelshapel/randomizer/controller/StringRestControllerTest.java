package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.provider.OneParametersStringProvider;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = StringRestController.class)
class StringRestControllerTest extends AbstractRestControllerTest<String> {
    @ParameterizedTest
    @ArgumentsSource(OneParametersStringProvider.class)
    void get_WithoutParams_ShouldReturnRandomEntity(String value) {
        final RandomEntity<String> mockEntity = createMockEntity(value);
        when(getPrimitiveRandomizer().randomize()).thenReturn(mockEntity.getValue());
        when(getPrimitiveRandomizer().getGenericClass()).thenReturn(mockEntity.getType());

        MvcResult mvcResult = getMvcResult("");

        final RandomEntity<String> responseBody = getResponseBody(mvcResult);
        int status = mvcResult.getResponse().getStatus();

        assertThat(responseBody).isNotNull();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
        verify(getPrimitiveRandomizer(), times(1)).randomize();
    }

    @ParameterizedTest
    @ArgumentsSource(OneParametersStringProvider.class)
    void get_RangeAsParam_ShouldReturnRandomEntity(String value) {
        final RandomEntity<String> mockEntity = createMockEntity(value);
        final Range<Long> range = getRange();

        when(getPrimitiveRandomizer().randomize(range)).thenReturn(mockEntity.getValue());
        when(getPrimitiveRandomizer().getGenericClass()).thenReturn(mockEntity.getType());

        MvcResult mvcResult = getMvcResult(getBoundedPath());

        final RandomEntity<String> responseBody = getResponseBody(mvcResult);
        int status = mvcResult.getResponse().getStatus();

        assertThat(responseBody).isNotNull();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
        verify(getPrimitiveRandomizer(), times(1)).randomize(range);
    }

    @Override
    protected RandomEntity<String> createMockEntity(String value) {
        return new RandomEntity<>(value, String.class);
    }
}