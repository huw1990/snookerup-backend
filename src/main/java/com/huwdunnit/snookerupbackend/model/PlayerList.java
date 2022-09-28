package com.huwdunnit.snookerupbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Object to encapsulate a list of players.
 * @author Huw
 */
@Data
@AllArgsConstructor
public class PlayerList {

    private List<Player> players;
}
