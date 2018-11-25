package com.wevat.controller;

import com.wevat.model.Quote;
import com.wevat.model.User;
import com.wevat.model.dto.UserDetails;
import com.wevat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/wevat/api/v1/users")
@Api(description = "Operations on User object.")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get User by Id.", response = User.class)
    public User getUserById(@ApiParam(value = "Id", required = true) @PathVariable(value = "id") String id) {
        return userService.getUserById(id);
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all users.", response = User.class)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "/{id}/quotes", produces = "application/json")
    @ApiOperation(value = "Get all quotes associated with given user Id.", response = User.class)
    public Set<Quote> getAllQuotesByUserId(@ApiParam(value = "Id", required = true) @PathVariable(value = "id") String id) {
        User user = userService.getUserById(id);
        return user.getQuotes();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create user.", response = User.class, consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody UserDetails userDetails) throws Exception {
        return userService.createUser(userDetails);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Update user.", response = User.class, consumes = "application/json", produces = "application/json")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user specified with Id.")
    public void deleteUser(@ApiParam(value = "Id", required = true) @PathVariable(value = "id") String id) {
        userService.deleteUser(id);
    }
}
