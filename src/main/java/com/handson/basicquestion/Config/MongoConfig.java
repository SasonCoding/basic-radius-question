package com.handson.basicquestion.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() { return "basicQuestionDB"; }

    @Override
    public boolean autoIndexCreation() { return true; }

}
