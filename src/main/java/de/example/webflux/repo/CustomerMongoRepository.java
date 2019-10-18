package de.example.webflux.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import de.example.webflux.domain.Customer;

public interface CustomerMongoRepository extends ReactiveMongoRepository<Customer, String> {

}
