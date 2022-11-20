package com.huwdunnit.snookerupbackend.repositories;

import com.huwdunnit.snookerupbackend.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing the storing and retrieving player scores for a routine in the DB.
 * @author Huw
 */
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
