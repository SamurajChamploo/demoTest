package com.example.demo;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class FlyWayConfig {
    @Bean(initMethod = "migrate")
    public Flyway flywayConfiguration() {
        return Flyway.configure()
                .dataSource(dataSourceProperties().getUrl(), dataSourceProperties().getUsername(), dataSourceProperties().getPassword())
                .schemas("customers")
                .baselineOnMigrate(true)
                .load();
    }
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
}
