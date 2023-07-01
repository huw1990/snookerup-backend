package com.huwdunnit.snookerupbackend.model.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * JPA entity for a Spring Security authority.
 * @author Huw
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;
}
