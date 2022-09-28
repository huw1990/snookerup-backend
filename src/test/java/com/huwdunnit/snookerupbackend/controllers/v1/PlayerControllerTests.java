package com.huwdunnit.snookerupbackend.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huwdunnit.snookerupbackend.model.Player;
import com.huwdunnit.snookerupbackend.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
    PlayerRepository playerRepository;

    @Mock
    ResourceNotFoundAdvice resourceNotFoundAdvice;

    @InjectMocks
    PlayerController playerController;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(playerController)
                .setControllerAdvice(resourceNotFoundAdvice)
                .build();
    }

    @Test
    public void getAllPlayers_Should_ReturnAllPlayers() throws Exception {
        // Create variables
        Player player1 = new Player();
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");
        Player player2 = new Player();
        player2.setFirstName("Stephen");
        player2.setLastName("Hendry");
        player2.setEmail("7times@example.com");
        List<Player> players = List.of(player1, player2);

        //Set mock expectations
        when(playerRepository.findAll()).thenReturn(players);

        //Execute method under test
        mockMvc.perform(get("/api/v1/players/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.players", hasSize(2)));
    }

    @Test
    public void getPlayerById_Should_ReturnCorrectPlayer_When_IdExists() throws Exception {
        // Create variables
        Player player1 = new Player();
        player1.setId(1L);
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");

        //Set mock expectations
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));

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
        when(playerRepository.findById(700L)).thenReturn(Optional.ofNullable(null));

        //Execute method under test
        mockMvc.perform(get("/api/v1/players/700")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewPlayer_Should_ReturnNewPlayerWithId() throws Exception {
        // Create variables
        Player player1 = new Player();
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");
        Player player1WithId = new Player();
        player1WithId.setFirstName("Mark");
        player1WithId.setLastName("Williams");
        player1WithId.setEmail("markjw@example.com");
        player1WithId.setId(1L);

        //Set mock expectations
        when(playerRepository.save(player1)).thenReturn(player1WithId);

        //Execute method under test
        mockMvc.perform(post("/api/v1/players/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(player1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }
}