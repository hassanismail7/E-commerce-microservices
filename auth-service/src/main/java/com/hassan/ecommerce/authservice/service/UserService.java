package com.hassan.ecommerce.authservice.service;

import com.hassan.ecommerce.authservice.dto.UpdateUserRequest;
import com.hassan.ecommerce.authservice.entity.User;
import com.hassan.ecommerce.authservice.exception.UserNotFoundException;
import com.hassan.ecommerce.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hassan.ecommerce.authservice.exception.EmailAlreadyExistsException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " not found"));
    }


    public User updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " not found"));

        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {

            throw new EmailAlreadyExistsException("Email is already registered.");
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }


    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " not found"));

        userRepository.delete(user);
    }
}
