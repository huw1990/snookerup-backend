package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.security.User;
import com.huwdunnit.snookerupbackend.web.model.UserDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Mapstruct mapping of User objects, i.e. the UserMapper class.
 *
 * @author Huw
 */
public class UserMapperTests {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void userToUserDto_Should_ReturnDtoWithCorrectValues() {
        //Create variables
        String firstName = "Mark";
        String lastName = "Williams";
        String email = "markjw@example.com";
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        //Set mock expectations

        //Execute method under test
        UserDto userDto = userMapper.userToUserDto(user);
        assertEquals(firstName, userDto.getFirstName());
        assertEquals(lastName, userDto.getLastName());
        assertEquals(email, userDto.getEmail());
    }

    @Test
    public void userDtoToUser_Should_ReturnRoutineWithCorrectValues() {
        //Create variables
        String firstName = "Mark";
        String lastName = "Williams";
        String email = "markjw@example.com";
        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);

        //Set mock expectations

        //Execute method under test
        User user = userMapper.userDtoToUser(userDto);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
    }
}
