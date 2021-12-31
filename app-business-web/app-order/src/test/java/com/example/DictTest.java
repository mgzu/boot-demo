package com.example;

import com.example.framework.Dict;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
public class DictTest extends BaseTest {

    @Test
    public void Test() throws JsonProcessingException {
        List<Dict> dicts = new ArrayList<>();
        addDict("string", "123", dicts);
        addDict("int", "123", dicts);
        addDict("bool", "true", dicts);
        addDict("decimal", "1.333333333333333333333333", dicts);
        dicts.forEach(i -> {
            switch (i.getDictType()) {
                case "int":
                    i.setValue(Integer.parseInt(i.getValue().toString()));
                    break;
                case "decimal":
                    i.setValue(new BigDecimal(i.getValue().toString()));
                    break;
                case "bool":
                    i.setValue(Boolean.parseBoolean(i.getValue().toString()));
                    break;
                default:
                    break;
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
