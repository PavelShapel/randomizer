//package com.pavelshapel.randomizer.controller;
//
//import com.pavelshapel.randomizer.entity.RandomEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.Date;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.*;
//
//@WebMvcTest(controllers = DateRestController.class)
//class DateRestControllerTest extends AbstractRestControllerTest<Date> {
//    @Test
//    void getRandomEntity_WithDefaultRange_ShouldReturnRandomEntity() {
//        when(getRandomizer().getRandomEntity()).thenReturn(getMockRandomEntity());
//
//        MvcResult mvcResult = getMvcResult(getMockRandomEntity());
//        final RandomEntity<Date> randomEntityFromResponse = getRandomEntityFromResponse(mvcResult);
//        assertThat(randomEntityFromResponse.toString()).isEqualToIgnoringCase((getMockRandomEntity().toString()));
//
//        int status = mvcResult.getResponse().getStatus();
//        assertThat(status).isEqualTo(HttpStatus.OK.value());
//
//        verify(getRandomizer(), times(1)).getRandomEntity();
//    }
//
//    @Override
//    protected RandomEntity<Date> getMockRandomEntity() {
//        return new RandomEntity<>(new Date(), Date.class);
//    }
//}