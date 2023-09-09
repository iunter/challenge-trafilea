package com.ivan.trafilea.challenge.repository;

import com.ivan.trafilea.challenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
