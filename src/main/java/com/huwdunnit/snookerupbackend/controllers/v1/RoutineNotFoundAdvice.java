package com.huwdunnit.snookerupbackend.controllers.v1;

import com.huwdunnit.snookerupbackend.controllers.exceptions.RoutineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice to convert a RoutineNotFoundException into a 404 NOT FOUND response.
 *
 * @author Huw
 */
@ControllerAdvice
class RoutineNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RoutineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(RoutineNotFoundException ex) {
        return ex.getMessage();
    }
}