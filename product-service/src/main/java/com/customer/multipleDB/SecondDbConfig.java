package com.customer.multipleDB;


import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

// Billing DB (MySQL)
@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.billing.repo",
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTxManager"
)
public class SecondDbConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.second")
    public DataSource secondDataSource() { return DataSourceBuilder.create().build(); }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("secondDataSource") DataSource ds) {
        return builder
                .dataSource(ds)
                .packages("com.example.billing.entity")
                .persistenceUnit("second")
                .build();
    }

    @Bean
    public PlatformTransactionManager secondTxManager(
            @Qualifier("secondEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
