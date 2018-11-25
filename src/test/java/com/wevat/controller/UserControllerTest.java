package com.wevat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wevat.exception.UserNotFoundException;
import com.wevat.model.User;
import com.wevat.model.dto.UserDetails;
import com.wevat.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static com.wevat.TestDataSetup.getUser;
import static com.wevat.TestDataSetup.getUsers;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnUserById() throws Exception {
        User user = getUser().isPresent() ? getUser().get() : new User();
        when(userService.getUserById("test-id")).thenReturn(user);
        mvc.perform(get("/wevat/api/v1/users/test-id")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id", is("test-id")))
                .andExpect(jsonPath("payoutCurrency", is("AUD")));
    }

    @Test
    public void shouldReturnAllQuotesByUserId() throws Exception {
        User user = getUser().isPresent() ? getUser().get() : new User();
        when(userService.getUserById("test-id")).thenReturn(user);
        mvc.perform(get("/wevat/api/v1/users/test-id/quotes")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
        List<User> users = getUsers();
        when(userService.getAllUsers()).thenReturn(users);
        mvc.perform(get("/wevat/api/v1/users")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("test-id")))
                .andExpect(jsonPath("$[0].payoutCurrency", is("AUD")));
    }

    @Test
    public void shouldCreateUserWithGivenDetails() throws Exception {
        User user = getUser().isPresent() ? getUser().get() : new User();
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("test-first-name");
        userDetails.setLastName("test-last-name");
        userDetails.setPayoutCurrency("AUD");
        String userDetailsJson = convertObjectToJsonBytes(userDetails);

        when(userService.createUser(Mockito.any())).thenReturn(user);
        mvc.perform(post("/wevat/api/v1/users")
                .contentType("application/json")
                .accept("application/json")
                .content(userDetailsJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id", is("test-id")))
                .andExpect(jsonPath("firstName", is("test-first-name")))
                .andExpect(jsonPath("lastName", is("test-last-name")))
                .andExpect(jsonPath("payoutCurrency", is("AUD")));
    }

    @Test
    public void shouldUpdateUserWithGivenDetails() throws Exception {
        User user = getUser().isPresent() ? getUser().get() : new User();
        String userJson = convertObjectToJsonBytes(user);

        when(userService.updateUser(Mockito.any())).thenReturn(user);
        mvc.perform(put("/wevat/api/v1/users")
                .contentType("application/json")
                .accept("application/json")
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id", is("test-id")))
                .andExpect(jsonPath("firstName", is("test-first-name")))
                .andExpect(jsonPath("lastName", is("test-last-name")))
                .andExpect(jsonPath("payoutCurrency", is("AUD")));
    }

    @Test
    public void shouldDeleteUserExistWithGivenId() throws Exception {
        doNothing().when(userService).deleteUser("test-id");
        mvc.perform(delete("/wevat/api/v1/users/test-id")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowAnExceptionForGivenInvalidUserId() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).getUserById("test-user-id");
        mvc.perform(get("/wevat/api/v1/users/test-user-id")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    private String convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }


}
