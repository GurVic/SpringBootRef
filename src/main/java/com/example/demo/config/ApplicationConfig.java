package com.example.demo.config;

import com.example.demo.adapter.postgres.PostgresqlVehicleRepository;
import com.example.demo.domain.VehicleRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class ApplicationConfig {

    private final AppProperties appProperties;

    ApplicationConfig(final AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    VehicleRepository vehicleRepository(final NamedParameterJdbcOperations namedParameterJdbcOperations) {
        return new PostgresqlVehicleRepository(namedParameterJdbcOperations);
    }
}
