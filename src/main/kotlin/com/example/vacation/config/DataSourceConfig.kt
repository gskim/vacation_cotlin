package com.example.vacation.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager",
    basePackages = ["com.example.vacation"]
)
class DataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    fun dataSource(): DataSource {
        val dataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

        // UTF-8
        dataSource.connectionInitSql = "SET NAMES utf8mb4"

        return dataSource
    }

    @Primary
    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(this.dataSource())
            .packages("com.example.vacation")
            .persistenceUnit("vacation")
            .build()
    }

    @Primary
    @Bean
    fun transactionManager(builder: EntityManagerFactoryBuilder): JpaTransactionManager {
        return JpaTransactionManager(entityManagerFactory(builder).`object`!!)
    }
}