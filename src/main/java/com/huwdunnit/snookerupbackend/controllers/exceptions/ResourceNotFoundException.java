package com.huwdunnit.snookerupbackend.controllers.exceptions;

/**
 * Exception thrown when unable to find a resource.
 *
 * @author Huw
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Could not find resource " + id);
    }
}
