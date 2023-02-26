package com.itnexusglobal.person.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.itnexusglobal.person")
@EnableJpaRepositories("com.itnexusglobal.person")
@EnableTransactionManagement
public class DomainConfig {
}
