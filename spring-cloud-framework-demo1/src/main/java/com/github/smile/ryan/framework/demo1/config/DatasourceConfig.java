package com.github.smile.ryan.framework.demo1.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * <pre>
 * 名称：DatasourceConfig
 * 描述：数据源配置
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@Configuration
// TODO 解决报错
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DatasourceConfig {

  private String username;
  private String url;
  private String password;
  private String driverClassName;

  @Bean
  @Primary
  public DataSource dataSource() {
    log.info(this.url + ":" + this.username);
    DruidDataSource ds = new DruidDataSource();
    ds.setUsername(this.username);
    ds.setPassword(this.password);
    ds.setDriverClassName(this.driverClassName);
    ds.setUrl(this.url);
    return ds;
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

}
