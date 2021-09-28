package com.example.demo.bad;

import com.example.demo.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class CastObjectTest extends BaseTest {

    @Test
    public void testCast() {
        Map<String, String> map = new HashMap<>();
        CastObject castObject = new CastObject();
        castObject.setA1("a1");
        castObject.setB1("b1");
        // error
//        map.put("a", (String) castObject);
    }

    class CastObject {
        private String a1;
        private String b1;

        public String getA1() {
            return a1;
        }

        public void setA1(String a1) {
            this.a1 = a1;
        }

        public String getB1() {
            return b1;
        }

        public void setB1(String b1) {
            this.b1 = b1;
        }
    }
}
