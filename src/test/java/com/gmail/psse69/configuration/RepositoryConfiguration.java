package com.gmail.psse69.configuration;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.gmail.psse69.model"})
@EnableJpaRepositories(basePackages = {"com.gmail.psse69.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
