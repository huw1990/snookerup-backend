package com.huwdunnit.snookerupbackend.web.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huwdunnit.snookerupbackend.services.UserService;
import com.huwdunnit.snookerupbackend.web.model.UserDto;
import com.huwdunnit.snookerupbackend.web.model.UserDtoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the UserController class.
 *
 * @author Huw
 */
public class UserControllerTests {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void getAllUsers_Should_ReturnAllUsers() throws Exception {
        // Create variables
        UserDto user1 = new UserDto();
        user1.setFirstName("Mark");
        user1.setLastName("Williams");
        user1.setEmail("markjw@example.com");
        UserDto user2 = new UserDto();
        user2.setFirstName("Stephen");
        user2.setLastName("Hendry");
        user2.setEmail("7times@example.com");
        List<UserDto> users = List.of(user1, user2);
        UserDtoList userDtoList = new UserDtoList(users);

        //Set mock expectations
        when(userService.listUsers()).thenReturn(userDtoList);

        //Execute method under test
        mockMvc.perform(get("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(2)));
    }

    @Test
    public void getUserById_Should_ReturnCorrectUser_When_IdExists() throws Exception {
        // Create variables
        UserDto user1 = new UserDto();
        user1.setId(1L);
        user1.setFirstName("Mark");
        user1.setLastName("Williams");
        user1.setEmail("markjw@example.com");

        //Set mock expectations
        when(userService.findUserById(1L)).thenReturn(user1);

        //Execute method under test
        mockMvc.perform(get("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void getUserById_Should_ReturnNotFound_When_IdDoesntExist() throws Exception {
        // Create variables

        //Set mock expectations
        when(userService.findUserById(700L)).thenThrow(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found, ID: 700"));

        //Execute method under test
        mockMvc.perform(get("/api/v1/users/700")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewUser_Should_ReturnNewUserWithId() throws Exception {
        // Create variables
        UserDto user1 = new UserDto();
        user1.setFirstName("Mark");
        user1.setLastName("Williams");
        user1.setEmail("markjw@example.com");
        UserDto user1WithId = new UserDto();
        user1WithId.setFirstName("Mark");
        user1WithId.setLastName("Williams");
        user1WithId.setEmail("markjw@example.com");
        user1WithId.setId(1L);

        //Set mock expectations
        when(userService.saveUser(user1)).thenReturn(user1WithId);

        //Execute method under test
        mockMvc.perform(post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void updateUser_Should_ReturnSuccess_When_UserFound() throws Exception {
        // Create variables
        UserDto user1 = new UserDto();
        user1.setId(1L);
        user1.setFirstName("Mark");
        user1.setLastName("Williams");
        user1.setEmail("markjw@example.com");
        UserDto updatedUser1 = new UserDto();
        updatedUser1.setId(1L);
        updatedUser1.setFirstName("Mark");
        updatedUser1.setLastName("Williams");
        updatedUser1.setEmail("markjw@snooker.com");

        //Set mock expectations

        //Execute method under test
        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser1)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateUser_Should_ReturnNotFound_When_UserNotFound() throws Exception {
        // Create variables
        UserDto updatedUser700 = new UserDto();
        updatedUser700.setId(700L);
        updatedUser700.setFirstName("Mark");
        updatedUser700.setLastName("Williams");
        updatedUser700.setEmail("markjw@snooker.com");

        //Set mock expectations
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found, ID: 700"))
                .when(userService).updateUser(700L, updatedUser700);

        mockMvc.perform(put("/api/v1/users/700")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser700)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUser_Should_ReturnBadRequest_When_UserIDsDontMatch() throws Exception {
        // Create variables
        UserDto updatedUser1 = new UserDto();
        updatedUser1.setId(1L);
        updatedUser1.setFirstName("Mark");
        updatedUser1.setLastName("Williams");
        updatedUser1.setEmail("markjw@snooker.com");

        //Set mock expectations
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "User IDs not matching"))
                .when(userService).updateUser(2L, updatedUser1);

        mockMvc.perform(put("/api/v1/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser1)))
                .andExpect(status().isBadRequest());
    }
}