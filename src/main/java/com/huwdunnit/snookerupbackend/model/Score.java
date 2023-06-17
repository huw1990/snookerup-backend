package com.huwdunnit.snookerupbackend.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * JPA entity for a score, submitted by a player against a particular routine.
 * @author Huw
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Routine routine;

    @ManyToOne
    private Player player;

    private int score;

    // When the score was made (note this could be different to when the entry was added to the DB!)
    private LocalDateTime dateMade;

    @Override
    public boolean equals(Object o) {
        //Only check equality through the ID field
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        return Objects.equals(id, score.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
