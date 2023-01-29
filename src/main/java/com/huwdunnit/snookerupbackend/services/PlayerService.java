package com.huwdunnit.snookerupbackend.services;

import com.huwdunnit.snookerupbackend.web.model.PlayerDto;
import com.huwdunnit.snookerupbackend.web.model.PlayerDtoList;

/**
 * Service for operations on players.
 *
 * @author Huw
 */
public interface PlayerService {

    /**
     * Get all players.
     * @return A list of player DTOs
     */
    PlayerDtoList listPlayers();

    /**
     * Find a player by its ID.
     * @param playerId The ID of the player
     * @return A DTO for the player with the provided ID
     */
    PlayerDto findPlayerById(Long playerId);

    /**
     * Save a player.
     * @param playerDto A DTO for the player to save
     * @return A DTO of the saved player (with extra ID after adding to the DB).
     */
    PlayerDto savePlayer(PlayerDto playerDto);
}
