package com.huwdunnit.snookerupbackend.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Object to encapsulate a list of user DTOs.
 * @author Huw
 */
@Data
@AllArgsConstructor
public class UserDtoList {

    private List<UserDto> users;
}
