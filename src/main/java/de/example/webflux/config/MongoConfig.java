package de.example.webflux.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import de.example.webflux.domain.Customer;
import de.example.webflux.repo.CustomerMongoRepository;

@EnableReactiveMongoRepositories(basePackageClasses = { CustomerMongoRepository.class })
@EntityScan(basePackageClasses = { Customer.class })
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "customers";
    }

}
