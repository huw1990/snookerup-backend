package com.huwdunnit.snookerupbackend.controllers.exceptions;

/**
 * Exception thrown when unable to find a routine.
 *
 * @author Huw
 */
public class RoutineNotFoundException extends RuntimeException{

    public RoutineNotFoundException(Long id) {
        super("Could not find routine " + id);
    }
}
