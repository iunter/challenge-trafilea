package com.ivan.trafilea.challenge.repository;

import com.ivan.trafilea.challenge.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT * FROM cart WHERE cart.USER_ID = :userId AND cart.IS_ACTIVE = TRUE", nativeQuery = true)
    public Optional<Cart> findByUserId (@Param("userId") String userId);
}
