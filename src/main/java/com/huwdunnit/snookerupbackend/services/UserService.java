package com.huwdunnit.snookerupbackend.services;

import com.huwdunnit.snookerupbackend.web.model.UserDto;
import com.huwdunnit.snookerupbackend.web.model.UserDtoList;

/**
 * Service for operations on users.
 *
 * @author Huw
 */
public interface UserService {

    /**
     * Get all users.
     * @return A list of user DTOs
     */
    UserDtoList listUsers();

    /**
     * Find a user by its ID.
     * @param userId The ID of the user
     * @return A DTO for the user with the provided ID
     */
    UserDto findUserById(Long userId);

    /**
     * Save a user.
     * @param userDto A DTO for the user to save
     * @return A DTO of the saved user (with extra ID after adding to the DB).
     */
    UserDto saveUser(UserDto userDto);

    /**
     * Updates a user.
     * @param userId The ID of the user to update
     * @param userDto A DTO of the user to update
     */
    void updateUser(Long userId, UserDto userDto);
}
