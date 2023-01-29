package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.Player;
import com.huwdunnit.snookerupbackend.web.model.PlayerDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Mapstruct mapping of Player objects, i.e. the PlayerMapper class.
 *
 * @author Huw
 */
public class PlayerMapperTests {

    PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);

    @Test
    public void playerToPlayerDto_Should_ReturnDtoWithCorrectValues() {
        //Create variables
        String firstName = "Mark";
        String lastName = "Williams";
        String email = "markjw@example.com";
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);

        //Set mock expectations

        //Execute method under test
        PlayerDto playerDto = playerMapper.playerToPlayerDto(player);
        assertEquals(firstName, playerDto.getFirstName());
        assertEquals(lastName, playerDto.getLastName());
        assertEquals(email, playerDto.getEmail());
    }

    @Test
    public void playerDtoToPlayer_Should_ReturnRoutineWithCorrectValues() {
        //Create variables
        String firstName = "Mark";
        String lastName = "Williams";
        String email = "markjw@example.com";
        PlayerDto playerDto = new PlayerDto();
        playerDto.setFirstName(firstName);
        playerDto.setLastName(lastName);
        playerDto.setEmail(email);

        //Set mock expectations

        //Execute method under test
        Player player = playerMapper.playerDtoToPlayer(playerDto);
        assertEquals(firstName, player.getFirstName());
        assertEquals(lastName, player.getLastName());
        assertEquals(email, player.getEmail());
    }
}
