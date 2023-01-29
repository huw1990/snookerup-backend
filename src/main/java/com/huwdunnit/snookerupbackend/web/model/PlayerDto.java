package com.huwdunnit.snookerupbackend.web.model;

import lombok.*;

/**
 * External-facing DTO for a Player.
 *
 * @author Huw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

}
