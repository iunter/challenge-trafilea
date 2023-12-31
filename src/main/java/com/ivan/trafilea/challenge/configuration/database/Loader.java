package com.ivan.trafilea.challenge.configuration.database;

import com.ivan.trafilea.challenge.model.Product;
import com.ivan.trafilea.challenge.model.User;
import com.ivan.trafilea.challenge.model.enums.ECategory;
import com.ivan.trafilea.challenge.repository.ICartRepository;
import com.ivan.trafilea.challenge.repository.IProductRepository;
import com.ivan.trafilea.challenge.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Loader {
    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Bean
    CommandLineRunner initDatabase(IProductRepository productRepository, IUserRepository userRepository){
        return args -> {
            //LOADING PRODUCTS
            log.info("Loading " + productRepository.save(new Product("Expensive Coffee", ECategory.COFFEE, 100.00)));
            log.info("Loading " + productRepository.save(new Product("Cheap Coffee", ECategory.COFFEE, 50.00)));
            log.info("Loading " + productRepository.save(new Product("Expensive Equipment", ECategory.EQUIPMENT, 100.00)));
            log.info("Loading " + productRepository.save(new Product("Cheap Equipment", ECategory.EQUIPMENT, 50.00)));
            log.info("Loading " + productRepository.save(new Product("Expensive Accessory", ECategory.ACCESSORIES, 100.00)));
            log.info("Loading " + productRepository.save(new Product("Cheap Accessory", ECategory.ACCESSORIES, 50.00)));

            //LOADING USERS
            log.info("Loading " + userRepository.save(new User("user1", "1234")));
            log.info("Loading " + userRepository.save(new User("user2", "1234")));
            log.info("Loading " + userRepository.save(new User("user3", "1234")));
            log.info("Loading " + userRepository.save(new User("user4", "1234")));

        };
    }
}
