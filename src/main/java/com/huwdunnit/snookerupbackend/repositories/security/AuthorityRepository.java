package com.huwdunnit.snookerupbackend.repositories.security;

import com.huwdunnit.snookerupbackend.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Spring Security authorities.
 *
 * @author Huw
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
