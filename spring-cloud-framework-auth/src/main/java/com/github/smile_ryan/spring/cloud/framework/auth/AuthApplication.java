package com.github.smile_ryan.spring.cloud.framework.auth;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * <pre>
 * 名称：AuthApplication
 * 描述：AuthApplication.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.github.smile_ryan.spring.cloud.framework.auth.mapper")
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableRedisHttpSession(redisNamespace = "auth", maxInactiveIntervalInSeconds = 300)
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
