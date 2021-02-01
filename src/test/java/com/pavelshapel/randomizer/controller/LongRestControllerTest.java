//package com.pavelshapel.randomizer.controller;
//
//import com.pavelshapel.randomizer.entity.RandomEntity;
//import com.pavelshapel.randomizer.provider.OneParametersLongProvider;
//import org.apache.commons.lang3.Range;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.servlet.MvcResult;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.*;
//
//@WebMvcTest(controllers = LongRestController.class)
//class LongRestControllerTest extends AbstractRestControllerTest<Long> {
//    @ParameterizedTest
//    @ArgumentsSource(OneParametersLongProvider.class)
//    void get_WithoutParams_ShouldReturnRandomEntity(Long value) {
//        final RandomEntity<Long> mockEntity = createMockEntity(value);
//        when(getPrimitiveRandomizer().randomize()).thenReturn(mockEntity.getValue());
//        when(getPrimitiveRandomizer().getGenericClass()).thenReturn(mockEntity.getType());
//
//        MvcResult mvcResult = getMvcResult("");
//
//        final RandomEntity<Long> responseBody = getResponseBody(mvcResult);
//        int status = mvcResult.getResponse().getStatus();
//
//        assertThat(responseBody).isNotNull();
//        assertThat(status).isEqualTo(HttpStatus.OK.value());
//        verify(getPrimitiveRandomizer(), times(1)).randomize();
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(OneParametersLongProvider.class)
//    void get_RangeAsParam_ShouldReturnRandomEntity(Long value) {
//        final RandomEntity<Long> mockEntity = createMockEntity(value);
//        final Range<Long> range = getRange();
//
//        when(getPrimitiveRandomizer().randomize(range)).thenReturn(mockEntity.getValue());
//        when(getPrimitiveRandomizer().getGenericClass()).thenReturn(mockEntity.getType());
//
//        MvcResult mvcResult = getMvcResult(getBoundedPath());
//
//        final RandomEntity<Long> responseBody = getResponseBody(mvcResult);
//        int status = mvcResult.getResponse().getStatus();
//
//        assertThat(responseBody).isNotNull();
//        assertThat(status).isEqualTo(HttpStatus.OK.value());
//        verify(getPrimitiveRandomizer(), times(1)).randomize(range);
//    }
//
//    @Override
//    protected RandomEntity<Long> createMockEntity(Long value) {
//        return new RandomEntity<>(value, Long.class);
//    }
//}