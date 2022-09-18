package com.huwdunnit.snookerupbackend.repositories;

import com.huwdunnit.snookerupbackend.model.Player;
import org.springframework.data.repository.CrudRepository;
/**
 * Repository for managing the storing and retrieving player information in the DB.
 * @author Huw
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
