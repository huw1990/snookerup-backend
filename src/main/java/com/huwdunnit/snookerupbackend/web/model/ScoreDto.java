package com.huwdunnit.snookerupbackend.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * External-facing DTO for a Score.
 *
 * @author Huw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScoreDto {

    private Long id;

    private Long playerId;

    private Long routineId;

    private int score;

}
