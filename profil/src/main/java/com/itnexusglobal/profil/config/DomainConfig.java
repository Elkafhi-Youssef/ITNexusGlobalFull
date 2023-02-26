package com.itnexusglobal.profil.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.itnexusglobal.profil")
@EnableJpaRepositories("com.itnexusglobal.profil")
@EnableTransactionManagement
public class DomainConfig {
}
