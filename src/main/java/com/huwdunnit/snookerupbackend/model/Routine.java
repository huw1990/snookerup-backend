package com.huwdunnit.snookerupbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * JPA entity for a routine, i.e. containing information such as the title and how to play.
 * @author Huw
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @Override
    public boolean equals(Object o) {
        //Only check equality through the ID field
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Routine routine = (Routine) o;

        return Objects.equals(id, routine.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
