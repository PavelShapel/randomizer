//package com.pavelshapel.randomizer.controller;
//
//import com.pavelshapel.randomizer.entity.RandomEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.servlet.MvcResult;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.*;
//
//@WebMvcTest(controllers = LongRestController.class)
//class LongRestControllerTest extends AbstractRestControllerTest<Long> {
//    @Test
//    void getRandomEntity_WithDefaultRange_ShouldReturnRandomEntity() {
//        when(getPrimitiveRandomizer().randomize()).thenReturn(getMockRandomEntity().getValue());
//        when(getUtilities().getSuperClassGenericType(any(),anyInt())).thenReturn(getMockRandomEntity().getType());
//
//
//        MvcResult mvcResult = getMvcResult(getMockRandomEntity());
//        final RandomEntity<Long> randomEntityFromResponse = getRandomEntityFromResponse(mvcResult);
//        assertThat(randomEntityFromResponse).isEqualTo(getMockRandomEntity());
//
//        int status = mvcResult.getResponse().getStatus();
//        assertThat(status).isEqualTo(HttpStatus.OK.value());
//
//        verify(getPrimitiveRandomizer(), times(1)).randomize();
//    }
//
//    @Override
//    protected RandomEntity<Long> getMockRandomEntity() {
//        return new RandomEntity<>(123456L, Long.class);
//    }
//}