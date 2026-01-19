package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
    
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(
            "mongodb+srv://prabhav24bcs10358_db_user:cUkzDKz2hrMC4yuC@cluster0.fudlh64.mongodb.net/ecommerce"
        );
    }
}
