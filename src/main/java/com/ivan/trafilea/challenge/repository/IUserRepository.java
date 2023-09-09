package com.ivan.trafilea.challenge.repository;

import com.ivan.trafilea.challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {

}
