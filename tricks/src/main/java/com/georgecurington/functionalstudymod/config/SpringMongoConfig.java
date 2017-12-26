package com.georgecurington.functionalstudymod.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
// @EnableMongoRepositories("demo.mongo.model")
public class SpringMongoConfig extends AbstractMongoConfiguration {
    @Value("${spring.profiles.active}")
    private String profileActive;

    @Value("${spring.application.name}")
    private String proAppName;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    @Override
    public MongoMappingContext mongoMappingContext()
        throws ClassNotFoundException {
        // TODO Auto-generated method stub
        return super.mongoMappingContext();
    }
    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoHost + ":" + mongoPort);
    }
    @Override
    protected String getDatabaseName() {
        // TODO Auto-generated method stub
        return mongoDB;
    }
}