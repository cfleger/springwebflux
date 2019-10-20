package de.example.webflux.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import de.example.webflux.domain.Customer;
import reactor.core.publisher.Flux;

public interface CustomerMongoRepository extends ReactiveMongoRepository<Customer, String> {

    public Flux<Customer> findByLastName(String lastName);

}
