package com.ivan.trafilea.challenge.repository;

import com.ivan.trafilea.challenge.model.Cart;
import com.ivan.trafilea.challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {

}
