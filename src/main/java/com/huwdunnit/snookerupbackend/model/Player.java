package com.huwdunnit.snookerupbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * JPA entity for a player (i.e. somebody that attempts routines and submits scores).
 * @author Huw
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Override
    public boolean equals(Object o) {
        //Only check equality through the ID field
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player user = (Player) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
