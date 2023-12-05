package com.example.backendchallengepicpay.picpaychallenge.service;

import com.example.backendchallengepicpay.picpaychallenge.domain.user.User;
import com.example.backendchallengepicpay.picpaychallenge.domain.user.UserType;
import com.example.backendchallengepicpay.picpaychallenge.dto.UserDTO;
import com.example.backendchallengepicpay.picpaychallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() != UserType.COMMON) {
            throw new Exception("Common user is not authorized to make the transaction.");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds");
        }
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        return saveUser(newUser);

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void removeAllUsers() {
        userRepository.deleteAll();
    }



}
