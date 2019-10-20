package de.example.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.example.webflux.domain.Customer;
import de.example.webflux.repo.CustomerMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerMongoRepository customerMongoRepository;

    @GetMapping("/{id}")
    private ResponseEntity<Mono<Customer>> read(@PathVariable final String id) {
        return ResponseEntity.ok().body(customerMongoRepository.findById(id));
    }

    @GetMapping()
    private ResponseEntity<Flux<Customer>> byLastName(@RequestParam final String lastName) {
        return ResponseEntity.ok().body(customerMongoRepository.findByLastName(lastName));
    }

    @PutMapping
    private ResponseEntity<Mono<Customer>> create(@RequestBody final Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMongoRepository.insert(customer));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Mono<Void>> delete(@PathVariable final String id) {
        return ResponseEntity.ok().body(customerMongoRepository.deleteById(id));
    }

    private Customer customer(final String id) {
        Customer customer = new Customer();
        customer.id = id;
        customer.firstName = "Dummy firstName";
        customer.lastName = "Dummy lastName";
        return customer;
    }

}
