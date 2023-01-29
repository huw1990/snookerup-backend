package com.huwdunnit.snookerupbackend.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * External-facing DTO for a Routine.
 *
 * @author Huw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoutineDto {

    private Long id;

    private String title;

    private String description;

}
