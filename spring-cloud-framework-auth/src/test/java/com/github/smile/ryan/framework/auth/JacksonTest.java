package com.github.smile.ryan.framework.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * 名称：JacksonTest
 * 描述：JacksonTest.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class JacksonTest {

  public static void main(String[] args) throws IOException {
    ObjectMapper objectMapper = new XmlMapper();

    Map<String, Set<String>> map = new HashMap<>();
    Set<String> sets = new HashSet<>();
    sets.add("1");
    sets.add("21");

//    map.put("name", "zhangsan");
    map.put("values", sets);

    System.out.println(objectMapper.writeValueAsString(map));

  }

}
