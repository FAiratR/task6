package edu.t1.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class MyConfiguration {
    @Bean(name="dataSourceMy")
    public HikariDataSource dataSourceMy() {
        Properties properties = new Properties();
        HikariConfig config = new HikariConfig();

        try {
            properties.load(new FileInputStream("moduleTask5/src/main/resources/application.properties"));
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