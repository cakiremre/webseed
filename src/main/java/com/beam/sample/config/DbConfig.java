package com.beam.sample.config;

import com.mongodb.MongoClient;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.spi.PersistenceProvider;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableMongoRepositories(basePackages = {"com.beam.sample.repository.mongo"})
@EnableJpaRepositories(basePackages = {"com.beam.sample.repository.jpa"})
public class DbConfig {

    @Bean
    public DriverManagerDataSource dataSource() throws IOException {

        Properties dbProps = new Properties();
        dbProps.load(AppConfig.class.getClassLoader().getResourceAsStream("db.properties"));

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbProps.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(dbProps.getProperty("jdbc.url"));
        dataSource.setUsername(dbProps.getProperty("jdbc.username"));
        dataSource.setPassword(dbProps.getProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(true);

        return vendorAdapter;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean emf() throws IOException {

        Properties jpaProps = new Properties();
        jpaProps.load(AppConfig.class.getClassLoader().getResourceAsStream("jpa.properties"));

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setPackagesToScan("com.beam.sample.model.jpa");
        emf.setJpaProperties(jpaProps);
        emf.setPersistenceProvider(persistenceProvider());

        return emf;
    }

    @Bean
    public PersistenceProvider persistenceProvider() {
        return new HibernatePersistenceProvider();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager() throws IOException {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf().getObject());

        return txManager;
    }

    public
    @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "web");
    }

    public
    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

}
