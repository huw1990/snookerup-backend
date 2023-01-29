package com.huwdunnit.snookerupbackend.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huwdunnit.snookerupbackend.model.Player;
import com.huwdunnit.snookerupbackend.services.PlayerService;
import com.huwdunnit.snookerupbackend.web.controllers.v1.PlayerController;
import com.huwdunnit.snookerupbackend.web.model.PlayerDto;
import com.huwdunnit.snookerupbackend.web.model.PlayerDtoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the PlayerController class.
 *
 * @author Huw
 */
public class PlayerControllerTests {

    @Mock
    PlayerService playerService;

    @InjectMocks
    PlayerController playerController;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(playerController)
                .build();
    }

    @Test
    public void getAllPlayers_Should_ReturnAllPlayers() throws Exception {
        // Create variables
        PlayerDto player1 = new PlayerDto();
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");
        PlayerDto player2 = new PlayerDto();
        player2.setFirstName("Stephen");
        player2.setLastName("Hendry");
        player2.setEmail("7times@example.com");
        List<PlayerDto> players = List.of(player1, player2);
        PlayerDtoList playerDtoList = new PlayerDtoList(players);

        //Set mock expectations
        when(playerService.listPlayers()).thenReturn(playerDtoList);

        //Execute method under test
        mockMvc.perform(get("/api/v1/players/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.players", hasSize(2)));
    }

    @Test
    public void getPlayerById_Should_ReturnCorrectPlayer_When_IdExists() throws Exception {
        // Create variables
        PlayerDto player1 = new PlayerDto();
        player1.setId(1L);
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");

        //Set mock expectations
        when(playerService.findPlayerById(1L)).thenReturn(player1);

        //Execute method under test
        mockMvc.perform(get("/api/v1/players/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void getPlayerById_Should_ReturnNotFound_When_IdDoesntExist() throws Exception {
        // Create variables

        //Set mock expectations
        when(playerService.findPlayerById(700L)).thenThrow(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Player Not Found, ID: 700"));

        //Execute method under test
        mockMvc.perform(get("/api/v1/players/700")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewPlayer_Should_ReturnNewPlayerWithId() throws Exception {
        // Create variables
        PlayerDto player1 = new PlayerDto();
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");
        PlayerDto player1WithId = new PlayerDto();
        player1WithId.setFirstName("Mark");
        player1WithId.setLastName("Williams");
        player1WithId.setEmail("markjw@example.com");
        player1WithId.setId(1L);

        //Set mock expectations
        when(playerService.savePlayer(player1)).thenReturn(player1WithId);

        //Execute method under test
        mockMvc.perform(post("/api/v1/players/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(player1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }
}