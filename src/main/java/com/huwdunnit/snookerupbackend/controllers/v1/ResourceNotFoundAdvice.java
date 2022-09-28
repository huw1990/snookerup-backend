package com.huwdunnit.snookerupbackend.controllers.v1;

import com.huwdunnit.snookerupbackend.controllers.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice to convert a ResourceNotFoundException into a 404 NOT FOUND response.
 *
 * @author Huw
 */
@ControllerAdvice
class ResourceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String resourceNotFoundHandler(ResourceNotFoundException ex) {
        return ex.getMessage();
    }
}