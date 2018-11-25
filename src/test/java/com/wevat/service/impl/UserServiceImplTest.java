package com.wevat.service.impl;

import com.wevat.exception.UserNotFoundException;
import com.wevat.model.User;
import com.wevat.model.dto.UserDetails;
import com.wevat.respository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.wevat.TestDataSetup.getUser;
import static com.wevat.TestDataSetup.getUsers;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldReturnUserForGivenValidId() {
        when(userRepository.findById("test-id")).thenReturn(getUser());

        User actualUser = userService.getUserById("test-id");

        assertNotNull(actualUser);
        assertThat(actualUser.getId(), is("test-id"));
        assertThat(actualUser.getPayoutCurrency(), is("AUD"));
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowExceptionWhenIdIsInvalid() {
        when(userRepository.findById("test-id")).thenThrow(UserNotFoundException.class);
        userService.getUserById("test-id");
    }

    @Test
    public void shouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(getUsers());

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertThat(users.get(0).getId(), is("test-id"));
    }

    @Test
    public void shouldCreateUserFromUserDetails() {
        User expectedUser = getUser().isPresent() ? getUser().get() : new User();
        when(userRepository.save(any())).thenReturn(expectedUser);

        User user = userService.createUser(new UserDetails());

        assertNotNull(user);
        assertThat(user.getId(), is("test-id"));
    }

    @Test
    public void shouldUpdateUserWithGivenFields() {
        User user = getUser().isPresent() ? getUser().get() : new User();
        when(userRepository.findById(any())).thenReturn(getUser());
        when(userRepository.save(any())).thenReturn(user);

        User actualUser = userService.updateUser(user);

        assertNotNull(actualUser);
        assertThat(actualUser.getId(), is("test-id"));
        assertThat(actualUser.getPayoutCurrency(), is("AUD"));
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowExceptionWhileUpdatingUserWhenIdIsInvalid() {
        when(userRepository.findById("test-id")).thenThrow(UserNotFoundException.class);
        User user = getUser().isPresent() ? getUser().get() : new User();
        userService.updateUser(user);
    }

    @Test
    public void shouldDeleteUser() {
        doNothing().when(userRepository).deleteById("test-id");
        userService.deleteUser("test-id");
    }


}
