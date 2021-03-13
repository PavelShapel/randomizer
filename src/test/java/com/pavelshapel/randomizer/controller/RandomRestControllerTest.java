package com.pavelshapel.randomizer.controller;

import com.pavelshapel.random.spring.boot.starter.StarterAutoConfiguration;
import com.pavelshapel.stream.spring.boot.starter.util.StreamUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.pavelshapel.random.spring.boot.starter.StarterAutoConfiguration.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebMvcTest(properties = {
        PREFIX + "." + PROPERTY_NAME + "=" + TRUE
},
        controllers = RandomRestController.class)
@ContextConfiguration(classes = {
        StarterAutoConfiguration.class,
        StreamUtils.class
})
class RandomRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void randomize_WithoutParams_ShouldReturnRandomizedValue() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/long"))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();

        assertThat(responseBody).isNotNull();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }
}