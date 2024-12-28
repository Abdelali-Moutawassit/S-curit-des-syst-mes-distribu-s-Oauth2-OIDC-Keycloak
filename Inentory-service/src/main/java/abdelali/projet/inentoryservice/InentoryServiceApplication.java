package abdelali.projet.inentoryservice;

import abdelali.projet.inentoryservice.entities.Product;
import abdelali.projet.inentoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class InentoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InentoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Stream.of("Computer", "Printer", "Smartphone", "Tablet").forEach(name -> {
                Product product = new Product(name + "ID", name, Math.random() * 1000, (int) (Math.random() * 10));
                productRepository.save(product);
            });
            productRepository.findAll().forEach(System.out::println);
        };
    }

}
