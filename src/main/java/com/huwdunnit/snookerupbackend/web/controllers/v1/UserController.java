package com.huwdunnit.snookerupbackend.web.controllers.v1;

import com.huwdunnit.snookerupbackend.services.UserService;
import com.huwdunnit.snookerupbackend.web.model.UserDto;
import com.huwdunnit.snookerupbackend.web.model.UserDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.huwdunnit.snookerupbackend.web.controllers.v1.UserController.URL_PATH;

/**
 * REST controller for API requests related to users.
 *
 * @author Huw
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(URL_PATH)
public class UserController {

    static final String URL_PATH = "/api/v1/users";

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDtoList> getAllUsers(){
        UserDtoList userList = userService.listUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = userService.findUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto){
        UserDto savedDto = userService.saveUser(userDto);

        // Set the location of the newly created user
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", URL_PATH + savedDto.getId());

        return new ResponseEntity<>(savedDto, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        userService.updateUser(id, userDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
