package com.pavelshapel.randomizer.service.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavelshapel.randomizer.service.randomizer.primitive.StringPrimitiveRandomizer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {
        JsonConverter.class,
        ObjectMapper.class,
        StringPrimitiveRandomizer.class
})
class JsonConverterTest {
    private static final Integer ID = 1;
    private static final String NAME = "name";
    private static final String JSON_TESTER = String.format("{\"id\":%d,\"name\":\"%s\"}", ID, NAME);

    private final JsonConverter jsonConverter;
    private final StringPrimitiveRandomizer stringPrimitiveRandomizer;

    @Autowired
    JsonConverterTest(JsonConverter jsonConverter, StringPrimitiveRandomizer stringPrimitiveRandomizer) {
        this.jsonConverter = jsonConverter;
        this.stringPrimitiveRandomizer = stringPrimitiveRandomizer;
    }

    @Test
    void pojoToJson_ValidPojoAsParam_ShouldReturnJson() {
        Optional<String> optionalJson = jsonConverter.pojoToJson(createTester());
        final String json = optionalJson.orElseThrow(IllegalArgumentException::new);

        assertThat(json).isEqualTo(JSON_TESTER);
    }

    @Test
    void pojoToJson_NullAsParam_ShouldReturnOptionalEmpty() {
        final Optional<String> optionalJson = jsonConverter.pojoToJson(null);

        assertThat(optionalJson).isEmpty();
    }

    @Test
    void pojoToJson_InvalidPojoAsParam_ShouldReturnOptionalEmpty() {
        final Optional<String> optionalJson = jsonConverter.pojoToJson(stringPrimitiveRandomizer.randomize());

        assertThat(optionalJson).isEmpty();
    }

    @Test
    void jsonToPojo_ValidJsonAsParam_ShouldReturnPojo() {
        Optional<Tester> optionalTester = jsonConverter.jsonToPojo(JSON_TESTER, Tester.class);
        final Tester tester = optionalTester.orElseThrow(IllegalArgumentException::new);

        assertThat(tester).isEqualTo(createTester());
    }

    @Test
    void jsonToPojo_InvalidStringAsParam_ShouldReturnOptionalEmpty() {
        final Optional<Tester> optionalTester = jsonConverter.jsonToPojo(stringPrimitiveRandomizer.randomize(), Tester.class);

        assertThat(optionalTester).isEmpty();
    }

    @Test
    void jsonToPojo_NullStringAsParam_ShouldReturnOptionalEmpty() {
        final Optional<Tester> optionalTester = jsonConverter.jsonToPojo(null, Tester.class);

        assertThat(optionalTester).isEmpty();
    }

    @Test
    void jsonToPojo_NullClassAsParam_ShouldReturnOptionalEmpty() {
        final Optional<Tester> optionalTester = jsonConverter.jsonToPojo(JSON_TESTER, null);

        assertThat(optionalTester).isEmpty();
    }

    @Test
    void pojoToMap_ValidPojoAsParam_ShouldReturnMap() {
        Tester tester = createTester();
        Optional<Map<String, Object>> optionalMap = jsonConverter.pojoToMap(tester);
        final Map<String, Object> map = optionalMap.orElseThrow(IllegalArgumentException::new);

        assertThat(map.get("id")).isSameAs(tester.getId());
        assertThat(map.get("name")).isSameAs(tester.getName());
    }

    @Test
    void pojoToMap_NullAsParam_ShouldReturnOptionalEmpty() {
        Optional<Map<String, Object>> optionalMap = jsonConverter.pojoToMap(null);

        AssertionsForClassTypes.assertThat(optionalMap).isEmpty();
    }

    @Test
    void pojoToMap_InvalidPojoAsParam_ShouldReturnOptionalEmpty() {
        Optional<Map<String, Object>> optionalMap = jsonConverter.pojoToMap(stringPrimitiveRandomizer.randomize());

        AssertionsForClassTypes.assertThat(optionalMap).isEmpty();
    }

    @Test
    void mapToPojo_ValidMapAsParam_ShouldReturnPojo() {
        Map<String, Object> map = createTesterMap();

        Optional<Tester> optionalTester = jsonConverter.mapToPojo(map, Tester.class);
        final Tester tester = optionalTester.orElseThrow(IllegalArgumentException::new);

        assertThat(tester.getId()).isEqualTo(map.get("id"));
        assertThat(tester.getName()).isEqualTo(map.get("name"));
    }

    @Test
    void mapToPojo_NullMapAsParam_ShouldReturnPojo() {
        Optional<Tester> optionalTester = jsonConverter.mapToPojo(null, Tester.class);

        assertThat(optionalTester).isEmpty();
    }

    @Test
    void mapToPojo_NullClassAsParam_ShouldReturnPojo() {
        Optional<Tester> optionalTester = jsonConverter.mapToPojo(createTesterMap(), null);

        assertThat(optionalTester).isEmpty();
    }

    @Test
    void isValidJson_ValidParam_ShouldReturnTrue() {
        final boolean isValidJson = jsonConverter.isValidJson(JSON_TESTER);

        assertThat(isValidJson).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"null",NAME})
    void isValidJson_InvalidParam_ShouldReturnFalse(String json) {
        final boolean isValidJson = jsonConverter.isValidJson(json);

        assertThat(isValidJson).isFalse();
    }

    private Tester createTester() {
        return new Tester(ID, NAME);
    }

    private Map<String, Object> createTesterMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", ID);
        map.put("name", NAME);
        return map;
    }
}