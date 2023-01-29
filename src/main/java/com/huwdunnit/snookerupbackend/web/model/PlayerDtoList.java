package com.huwdunnit.snookerupbackend.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Object to encapsulate a list of player DTOs.
 * @author Huw
 */
@Data
@AllArgsConstructor
public class PlayerDtoList {

    private List<PlayerDto> players;
}
