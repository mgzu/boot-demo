package com.example;

import com.example.framework.web.Dict;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
class DictTest extends BaseTest {

    @Test
    void test() throws JsonProcessingException {
        List<Dict> dicts = new ArrayList<>();
        addDict("string", "123", dicts);
        addDict("int", "123", dicts);
        addDict("bool", "true", dicts);
        addDict("decimal", "1.333333333333333333333333", dicts);
        dicts.forEach(i -> {
            switch (i.getDictType()) {
                case "int" -> i.setValue(Integer.parseInt(i.getValue().toString()));
                case "decimal" -> i.setValue(new BigDecimal(i.getValue().toString()));
                case "bool" -> i.setValue(Boolean.parseBoolean(i.getValue().toString()));
                default -> {
                }
            }
        });

        dicts.forEach(System.out::println);
        System.out.println(objectMapper.writeValueAsString(dicts));
    }

    public void addDict(String type, String value, List<Dict> dicts) {
        Dict dict = new Dict();
        dict.setDictType(type);
        dict.setValue(value);
        dicts.add(dict);
    }

}
