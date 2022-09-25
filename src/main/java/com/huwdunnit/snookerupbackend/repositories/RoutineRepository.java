package com.huwdunnit.snookerupbackend.repositories;

import com.huwdunnit.snookerupbackend.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing the storing and retrieving routine information in the DB.
 * @author Huw
 */
public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
