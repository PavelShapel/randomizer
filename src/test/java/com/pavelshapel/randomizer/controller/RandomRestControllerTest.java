//package com.pavelshapel.randomizer.controller;
//
//import com.pavelshapel.random.spring.boot.starter.StarterAutoConfiguration;
//import com.pavelshapel.stream.spring.boot.starter.util.StreamUtils;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.stream.Stream;
//
//import static com.pavelshapel.random.spring.boot.starter.StarterAutoConfiguration.*;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.params.provider.Arguments.arguments;
//
//@WebMvcTest(properties = {
//        PREFIX + "." + PROPERTY_NAME + "=" + TRUE
//},
//        controllers = RandomRestController.class)
//@ContextConfiguration(classes = {
//        RandomRestController.class,
//        StarterAutoConfiguration.class,
//        StreamUtils.class
//})
//class RandomRestControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @ParameterizedTest
//    @MethodSource("generateTypes")
//    void randomize_WithoutParams_ShouldReturnRandomizedValue(String type) throws Exception {
//        MvcResult mvcResult = mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get("/{type}", type))
//                .andReturn();
//
//        String responseBody = mvcResult.getResponse().getContentAsString();
//        int status = mvcResult.getResponse().getStatus();
//
//        assertThat(responseBody).isNotNull();
//        assertThat(status).isEqualTo(HttpStatus.OK.value());
//    }
//
//    private static Stream<Arguments> generateTypes() {
//        return Stream.of(
//                arguments("long"),
//                arguments("boolean"),
//                arguments("string"),
//                arguments("date"),
//                arguments("double")
//        );
//    }
//}