package com.example.jpa_specification_example.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class SecurityConfig {
    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> Optional.of(1L);
    }
}
