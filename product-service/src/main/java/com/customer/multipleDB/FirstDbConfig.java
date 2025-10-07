package com.customer.multipleDB;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
// Inventory DB (Postgres)
@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.inventory.repo",
        entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstTxManager"
)
public class FirstDbConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.first")
    @Primary
    public DataSource firstDataSource() { return DataSourceBuilder.create().build(); }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(firstDataSource())
                .packages("com.example.inventory.entity")
                .persistenceUnit("first")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager firstTxManager(
            @Qualifier("firstEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
