package edu.t1.configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class PayConfiguration {
    @Bean(name="dataSourcePay")
    @Primary
    public HikariDataSource dataSourcePay() {
        Properties properties = new Properties();
        HikariConfig config = new HikariConfig();

        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        config.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
        config.setJdbcUrl(properties.getProperty("spring.datasource.url"));
        config.setUsername(properties.getProperty("spring.datasource.username"));
        config.setPassword(properties.getProperty("spring.datasource.password"));
        return new HikariDataSource(config);
    }
}