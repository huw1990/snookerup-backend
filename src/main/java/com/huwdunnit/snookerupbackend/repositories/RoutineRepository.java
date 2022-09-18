package com.huwdunnit.snookerupbackend.repositories;

import com.huwdunnit.snookerupbackend.model.Routine;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for managing the storing and retrieving routine information in the DB.
 * @author Huw
 */
public interface RoutineRepository extends CrudRepository<Routine, Long> {
}
