package com.huwdunnit.snookerupbackend.services;

import com.huwdunnit.snookerupbackend.web.model.RoutineDto;
import com.huwdunnit.snookerupbackend.web.model.RoutineDtoList;

/**
 * Service for operations on routines.
 *
 * @author Huw
 */
public interface RoutineService {

    /**
     * Get all routines.
     * @return A list of routine DTOs
     */
    RoutineDtoList listRoutines();

    /**
     * Find a routine by its ID.
     * @param routineId The ID of the routine
     * @return A DTO for the routine with the provided ID
     */
    RoutineDto findRoutineById(Long routineId);
}
