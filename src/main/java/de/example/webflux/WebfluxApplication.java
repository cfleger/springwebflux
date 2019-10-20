package de.example.webflux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import de.example.webflux.config.MongoConfig;

@SpringBootApplication
@Import({ MongoConfig.class })
public class WebfluxApplication implements CommandLineRunner {

    public static void main(final String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {

    }

}
