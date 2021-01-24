package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.converter.JsonConverter;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

@AutoConfigureMockMvc
@Import(JsonConverter.class)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
abstract
class AbstractRestControllerTest<T> {
    @Autowired
    JsonConverter jsonConverter;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PrimitiveRandomizer<T> primitiveRandomizer;

    @SneakyThrows
    protected MvcResult getMvcResult(RandomEntity<T> randomEntity) {
        return mockMvc
                .perform(MockMvcRequestBuilders
                        .get(String.format("/%s", randomEntity.getType().getSimpleName().toLowerCase()))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    }

    @SneakyThrows
    protected RandomEntity<T> getRandomEntityFromResponse(MvcResult mvcResult) {
        String content = mvcResult.getResponse().getContentAsString();
        final Optional<RandomEntity> optionalRandomEntity = jsonConverter.jsonToPojo(content, RandomEntity.class);
        return optionalRandomEntity.orElseThrow(IllegalArgumentException::new);
    }

    protected abstract RandomEntity<T> getMockRandomEntity();
}