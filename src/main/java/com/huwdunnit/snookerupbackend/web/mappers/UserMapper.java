package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.security.User;
import com.huwdunnit.snookerupbackend.web.model.UserDto;
import org.mapstruct.Mapper;

/**
 * Mapstruct mapper for moving between User DB entities and DTOs.
 *
 * @author Huw
 */
@Mapper
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
