package com.huwdunnit.snookerupbackend.repositories.security;

import com.huwdunnit.snookerupbackend.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository for Spring Security roles.
 *
 * @author Huw
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
