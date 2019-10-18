package de.example.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import de.example.webflux.config.MongoConfig;
import de.example.webflux.domain.Customer;
import de.example.webflux.repo.CustomerMongoRepository;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Import({ MongoConfig.class })
public class WebfluxApplication implements CommandLineRunner {

    @Autowired
    private CustomerMongoRepository reactiveCustomerRepository;

    public static void main(final String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        Mono<Customer> save = reactiveCustomerRepository.save(new Customer("Alice", "Smith"));
        Customer block = save.block();
        System.out.println("Customer id " + block.id);
        reactiveCustomerRepository.save(new Customer("Bob", "Smith"));
        reactiveCustomerRepository.save(new Customer("Charlie", "Smith"));
        Flux<Customer> findByFirstName = reactiveCustomerRepository.findAll();
        Disposable subscribe = findByFirstName.subscribe(c -> {
            System.out.println("Subscribed and found " + c.id);
        });

        while (!subscribe.isDisposed()) {

        }

    }

}
