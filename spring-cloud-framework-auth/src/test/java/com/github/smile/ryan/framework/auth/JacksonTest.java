package com.github.smile.ryan.framework.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
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
    Map<String, Object> map = new HashMap<>();
    Set<String> sets = new HashSet<>();
    sets.add("1");
    sets.add("21");
    map.put("name", "zhangsan");
    map.put("values", sets);
    String x = objectMapper.writeValueAsString(map);
    System.out.println(x);
    System.out.println(objectMapper.writeValueAsString(sets));
//    String y = "<HashMap><name><values>1</values><values>21</values></name><a><aa>1</aa></a></HashMap>";
    String y = "<HashMap><values>1</values><values>21</values></HashMap>";
    MapType collectionType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Set.class);
    System.out.println(objectMapper.readValue(x, Map.class));
    System.out.println(objectMapper.readValue(y, collectionType).toString());
  }

}
