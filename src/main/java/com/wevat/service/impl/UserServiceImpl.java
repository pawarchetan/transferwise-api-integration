package com.wevat.service.impl;

import com.wevat.exception.UserNotFoundException;
import com.wevat.model.User;
import com.wevat.model.dto.UserDetails;
import com.wevat.respository.UserRepository;
import com.wevat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id : '" + id + "'."));
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User createUser(UserDetails userDetails) {
        User user = new User();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPayoutCurrency(userDetails.getPayoutCurrency());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User persistedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with Id : '" + user.getId() + "'."));
        persistedUser.setFirstName(user.getFirstName());
        persistedUser.setLastName(user.getLastName());
        persistedUser.setPayoutCurrency(user.getPayoutCurrency());
        return userRepository.save(persistedUser);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
