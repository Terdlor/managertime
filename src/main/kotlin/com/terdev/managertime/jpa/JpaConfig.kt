package com.terdev.managertime.jpa

import com.terdev.managertime.jpa.JpaConfig.Companion.PACKAGE_SCAN_TM_DAO
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PropertiesLoaderUtils
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager


@Configuration
@EnableJpaRepositories(PACKAGE_SCAN_TM_DAO)
class JpaConfig {

    companion object {
        const val PACKAGE_SCAN_TM_DAO = "com.terdev.managertime.jpa.tm"

        const val PACKAGE_SCAN_TM_ENTITY = "com.terdev.managertime.jpa.tm.entity"
    }

    @Bean
    fun getHibernateProperties() = PropertiesLoaderUtils.loadAllProperties("hibernate.properties")


    @Bean
    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val emf = LocalContainerEntityManagerFactoryBean()
        emf.dataSource = dataSource
        emf.setPackagesToScan(PACKAGE_SCAN_TM_ENTITY)
        val vendorAdapter: JpaVendorAdapter = HibernateJpaVendorAdapter()
        emf.jpaVendorAdapter = vendorAdapter
        emf.setJpaProperties(getHibernateProperties())
        return emf
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(emf)
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun hikariConfig() = HikariConfig()

    @Bean
    fun dataSource() = HikariDataSource(hikariConfig())

}