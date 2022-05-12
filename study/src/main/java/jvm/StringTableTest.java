package jvm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zz on 2022/4/13.
 */
public class StringTableTest {

    //    private
    public static void main(String[] args) throws JsonProcessingException {
        String s1 = "a";
        String s2 = "a";
        s1 = "b";
        s1 += "c";

//        testJackson();
    }

    private static void testJackson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        while (true) {
            A a = new A();
            a.a = UUID.randomUUID().toString();
            a.map.put(UUID.randomUUID().toString(), "dsa");
            String string = objectMapper.writeValueAsString(a);
            A readA = objectMapper.readValue(string, A.class);
        }
    }



    @Data
    static class A implements Serializable {

        String a;
        Map<String, String> map = new HashMap<>();
    }
}
