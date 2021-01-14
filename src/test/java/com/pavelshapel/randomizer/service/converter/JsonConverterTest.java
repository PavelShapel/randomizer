package com.pavelshapel.randomizer.service.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pavelshapel.randomizer.service.randomizer.StringRandomizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {
        JsonConverter.class,
        StringRandomizer.class
})
class JsonConverterTest {
    private static final Integer ID = 1;
    private static final String NAME = "name";
    private static final String JSON_TESTER = String.format("{\"id\":%d,\"name\":\"%s\"}", ID, NAME);

    @Autowired
    JsonConverter jsonConverter;
    @Autowired
    StringRandomizer stringRandomizer;

    @Test
    void pojoToJson_ValidPojoAsParam_ShouldReturnJson() {
        String json = jsonConverter.pojoToJson(createTester());

        assertThat(json).isEqualTo(JSON_TESTER);
    }


    @Test
    void jsonToPojo_ValidJsonAsParam_ShouldReturnPojo() {
        Tester tester = jsonConverter.jsonToPojo(JSON_TESTER, Tester.class);

        assertThat(createTester()).isEqualTo(tester);
    }

    @Test
    void jsonToPojo_InvalidStringAsParam_ShouldThrowException() {
        Assertions.assertThrows(JsonProcessingException.class,
                () -> jsonConverter.jsonToPojo(stringRandomizer.randomize(), Tester.class));
    }

    @Test
    void pojoToMap_ValidPojoAsParam_ShouldReturnMap() {
        Tester tester = createTester();
        Map<String, Object> map = jsonConverter.pojoToMap(tester);

        assertThat(tester.getId()).isEqualTo(map.get("id"));
        assertThat(tester.getName()).isEqualTo(map.get("name"));
    }

    @Test
    void mapToPojo_ValidMapAsParam_ShouldReturnPojo() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", ID);
        map.put("name", NAME);

        Tester tester = jsonConverter.mapToPojo(map, Tester.class);

        assertThat(tester.getId()).isEqualTo(map.get("id"));
        assertThat(tester.getName()).isEqualTo(map.get("name"));
    }

    private Tester createTester() {
        return new Tester(ID, NAME);
    }
}