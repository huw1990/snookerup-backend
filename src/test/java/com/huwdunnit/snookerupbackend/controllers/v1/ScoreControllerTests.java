package com.huwdunnit.snookerupbackend.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huwdunnit.snookerupbackend.model.Player;
import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.repositories.ScoreRepository;
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
 * Tests for the ScoreController class.
 *
 * @author Huw
 */
public class ScoreControllerTests {

    @Mock
    ScoreRepository scoreRepository;

    @Mock
    ResourceNotFoundAdvice resourceNotFoundAdvice;

    @InjectMocks
    ScoreController scoreController;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(scoreController)
                .setControllerAdvice(resourceNotFoundAdvice)
                .build();
    }

    @Test
    public void getAllScores_Should_ReturnAllScores() throws Exception {
        // Create variables
        // Routines
        Routine routine1 = new Routine();
        routine1.setId(1L);
        routine1.setTitle("Title 1");
        routine1.setDescription("Description 1");
        Routine routine2 = new Routine();
        routine2.setId(2L);
        routine2.setTitle("Title 2");
        routine2.setDescription("Description 2");
        // Players
        Player player1 = new Player();
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");
        Player player2 = new Player();
        player2.setFirstName("Stephen");
        player2.setLastName("Hendry");
        player2.setEmail("7times@example.com");
        // Scores
        Score score1 = new Score();
        score1.setScore(78);
        score1.setRoutine(routine1);
        score1.setPlayer(player1);
        Score score2 = new Score();
        score2.setScore(120);
        score2.setRoutine(routine2);
        score2.setPlayer(player2);
        List<Score> scores = List.of(score1, score2);

        //Set mock expectations
        when(scoreRepository.findAll()).thenReturn(scores);

        //Execute method under test
        mockMvc.perform(get("/api/v1/scores/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scores", hasSize(2)));
    }

    @Test
    public void getScoreById_Should_ReturnCorrectScore_When_IdExists() throws Exception {
        // Create variables
        // Routine
        Routine routine1 = new Routine();
        routine1.setId(1L);
        routine1.setTitle("Title 1");
        routine1.setDescription("Description 1");
        // Player
        Player player1 = new Player();
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");
        // Score
        Score score1 = new Score();
        score1.setId(1L);
        score1.setScore(78);
        score1.setRoutine(routine1);
        score1.setPlayer(player1);

        //Set mock expectations
        when(scoreRepository.findById(1L)).thenReturn(Optional.of(score1));

        //Execute method under test
        mockMvc.perform(get("/api/v1/scores/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void getScoreById_Should_ReturnNotFound_When_IdDoesntExist() throws Exception {
        // Create variables

        //Set mock expectations
        when(scoreRepository.findById(700L)).thenReturn(Optional.ofNullable(null));

        //Execute method under test
        mockMvc.perform(get("/api/v1/scores/700")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewScore_Should_ReturnNewScoreWithId() throws Exception {
        // Create variables
        // Routine
        Routine routine1 = new Routine();
        routine1.setId(1L);
        routine1.setTitle("Title 1");
        routine1.setDescription("Description 1");
        // Player
        Player player1 = new Player();
        player1.setFirstName("Mark");
        player1.setLastName("Williams");
        player1.setEmail("markjw@example.com");
        // Score
        Score score1 = new Score();
        score1.setScore(78);
        score1.setRoutine(routine1);
        score1.setPlayer(player1);
        Score score1WithId = new Score();
        score1WithId.setScore(78);
        score1WithId.setRoutine(routine1);
        score1WithId.setPlayer(player1);
        score1WithId.setId(1L);

        //Set mock expectations
        when(scoreRepository.save(score1)).thenReturn(score1WithId);

        //Execute method under test
        mockMvc.perform(post("/api/v1/scores/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(score1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }
}