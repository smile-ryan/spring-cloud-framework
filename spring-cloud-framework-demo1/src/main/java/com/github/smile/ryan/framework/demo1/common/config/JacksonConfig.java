package com.github.smile.ryan.framework.demo1.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return jacksonObjectMapperBuilder ->
        jacksonObjectMapperBuilder
            .locale(Locale.CHINA)
            .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z"))
            .timeZone(TimeZone.getTimeZone("GMT+8"))
            .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
            .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }
}