package com.ivan.trafilea.challenge.repository;

import com.ivan.trafilea.challenge.model.ProductCart;
import com.ivan.trafilea.challenge.model.ProductCartKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductCartRepository extends JpaRepository<ProductCart, ProductCartKey> {

}
