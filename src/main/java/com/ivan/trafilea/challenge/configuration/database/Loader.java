package com.ivan.trafilea.challenge.configuration.database;

import com.ivan.trafilea.challenge.model.Product;
import com.ivan.trafilea.challenge.model.enums.ECategory;
import com.ivan.trafilea.challenge.repository.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Loader {
    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Bean
    CommandLineRunner initDatabase(IProductRepository repository){
        return args -> {
            log.info("Loading " + repository.save(new Product("Expensive Coffee", ECategory.COFFEE, 100)));
            log.info("Loading " + repository.save(new Product("Cheap Coffee", ECategory.COFFEE, 50)));
            log.info("Loading " + repository.save(new Product("Expensive Equipment", ECategory.EQUIPMENT, 100)));
            log.info("Loading " + repository.save(new Product("Cheap Equipment", ECategory.EQUIPMENT, 50)));
            log.info("Loading " + repository.save(new Product("Expensive Accessory", ECategory.ACCESSORIES, 100)));
            log.info("Loading " + repository.save(new Product("Cheap Accessory", ECategory.ACCESSORIES, 50)));
        };
    }
}
