package com.wevat.service;

import com.wevat.model.User;
import com.wevat.model.dto.UserDetails;

import java.util.List;

public interface UserService {

    User getUserById(String id);

    List<User> getAllUsers();

    User createUser(UserDetails userDetails) throws Exception;

    User updateUser(User user);

    void deleteUser(String id);
}
