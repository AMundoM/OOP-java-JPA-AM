package com.dxc.bankapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement

public class PersistenciaConfig {
    @Autowired
    private Environment env;


    @Bean // JPA transaction manager
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(clienteEmf().getObject());
        return transactionManager;
    }

    @Bean
    DataSource clienteDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(env.getProperty("mybankdb.url"));

        ds.setDriverClassName(env.getProperty("mybankdb.driverClassName"));
        ds.setUsername(env.getProperty("mybankdb.dbUserName"));
        ds.setPassword(env.getProperty("mybankdb.password"));

        return ds;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setShowSql(true);
        va.setGenerateDdl(true);

        return va;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean clienteEmf() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setPersistenceUnitName(env.getProperty("mybankdb.persistenceUnitName"));

        em.setDataSource(schoolDataSource());
        em.setPackagesToScan("com.dxc.mypersonalbankapi.models");
        em.setJpaVendorAdapter(vendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("mybankdb.dialect"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("mybanckdb.hbm2ddl"));
        return properties;
    }

}
