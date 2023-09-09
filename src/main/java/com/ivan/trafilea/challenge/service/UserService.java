package com.ivan.trafilea.challenge.service;

import com.ivan.trafilea.challenge.model.User;
import com.ivan.trafilea.challenge.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public User findById (String userId){
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException("The user with Id: " + userId + " could not be found"));
    }
}

class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String message) {
        super(message);
    }
}
