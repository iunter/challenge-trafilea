package com.ivan.trafilea.challenge.repository;

import com.ivan.trafilea.challenge.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {

}
