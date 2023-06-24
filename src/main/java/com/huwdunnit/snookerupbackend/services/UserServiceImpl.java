package com.huwdunnit.snookerupbackend.services;

import com.huwdunnit.snookerupbackend.model.security.User;
import com.huwdunnit.snookerupbackend.repositories.security.UserRepository;
import com.huwdunnit.snookerupbackend.web.mappers.UserMapper;
import com.huwdunnit.snookerupbackend.web.model.UserDto;
import com.huwdunnit.snookerupbackend.web.model.UserDtoList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the user service.
 *
 * @author Huw
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    /** Repository for DB operations related to users. */
    private final UserRepository userRepository;

    /** Mapper for going between DB entities and DTOs. */
    private final UserMapper userMapper;

    @Override
    public UserDtoList listUsers() {
        log.debug("listUsers");
        List<User> users = userRepository.findAll();
        log.debug("Number of users={}", users.size());
        List <UserDto> userDtos = users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
        return new UserDtoList(userDtos);
    }

    @Override
    public UserDto findUserById(Long userId) {
        log.debug("findUserById, userId={}", userId);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found, ID: " + userId));
        log.debug("Found user={}", user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        log.debug("saveUser, userDto={}", userDto);
        // Convert to a User to insert in the DB, then back to a DTO to return from the service
        User savedUser = userRepository.save(userMapper.userDtoToUser(userDto));
        log.debug("User saved with ID={}", savedUser.getId());
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public void updateUser(Long userId, UserDto userDto) {
        log.debug("updateUser, userId={} userDto={}", userId, userDto);
        if (userId != userDto.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User IDs not matching");
        }
        Optional<User> userOptional = userRepository.findById(userId);

        userOptional.ifPresentOrElse(user -> {
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            userRepository.save(user);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found. ID: " + userId);
        });
    }
}
