package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.converter.JsonConverter;
import com.pavelshapel.randomizer.service.randomizer.array.ArrayRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@Import({
        JsonConverter.class
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
abstract class AbstractRestControllerTest<T> {
    @Autowired
    JsonConverter jsonConverter;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PrimitiveRandomizer<T> primitiveRandomizer;
    @MockBean
    ArrayRandomizer<T> arrayRandomizer;

    protected void get(T value) {
        final RandomEntity<T> mockRandomEntity = createMockRandomEntity(value);
        when(primitiveRandomizer.randomize()).thenReturn(mockRandomEntity.getValue());
        when(primitiveRandomizer.getGenericClass()).thenReturn(mockRandomEntity.getType());

        MvcResult mvcResult = getMvcResult("");

        final RandomEntity<T> randomEntityFromResponse = getRandomEntityFromResponse(mvcResult);
        int status = mvcResult.getResponse().getStatus();

        assertThat(randomEntityFromResponse).isNotNull();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
        verify(primitiveRandomizer, times(1)).randomize();
    }

    protected void getByRange(T value) {
        final RandomEntity<T> mockRandomEntity = createMockRandomEntity(value);
        final Range<Long> range = getRange();
        when(primitiveRandomizer.randomize(range)).thenReturn(mockRandomEntity.getValue());
        when(primitiveRandomizer.getGenericClass()).thenReturn(mockRandomEntity.getType());

        MvcResult mvcResult = getMvcResult(getBoundedPath());

        final RandomEntity<T> randomEntityFromResponse = getRandomEntityFromResponse(mvcResult);
        int status = mvcResult.getResponse().getStatus();

        assertThat(randomEntityFromResponse).isNotNull();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
        verify(primitiveRandomizer, times(1)).randomize(range);
    }

    @SneakyThrows
    protected MvcResult getMvcResult(String path) {
        return mockMvc
                .perform(MockMvcRequestBuilders
                        .get(String.format("/%s%s", primitiveRandomizer.getGenericClass().getSimpleName().toLowerCase(), path))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    }

    @SneakyThrows
    protected RandomEntity<T> getRandomEntityFromResponse(MvcResult mvcResult) {
        String content = mvcResult.getResponse().getContentAsString();
        final Optional<RandomEntity> optionalRandomEntity = jsonConverter.jsonToPojo(content, RandomEntity.class);
        return optionalRandomEntity.orElseThrow(IllegalArgumentException::new);
    }

    private Range<Long> getRange() {
        return DEFAULT_POSITIVE_BYTE_RANGE.getValue();
    }

    private String getBoundedPath() {
        return String.format("/%d/%d", getRange().getMinimum(), getRange().getMaximum());
    }

    protected abstract RandomEntity<T> createMockRandomEntity(T value);
}