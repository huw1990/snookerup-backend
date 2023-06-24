package com.huwdunnit.snookerupbackend.repositories.security;

import com.huwdunnit.snookerupbackend.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for managing the storing and retrieving user information in the DB.
 * @author Huw
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
