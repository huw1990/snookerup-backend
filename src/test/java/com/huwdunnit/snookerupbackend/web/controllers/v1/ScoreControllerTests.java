package com.huwdunnit.snookerupbackend.web.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huwdunnit.snookerupbackend.services.ScoreService;
import com.huwdunnit.snookerupbackend.web.controllers.v1.ScoreController;
import com.huwdunnit.snookerupbackend.web.model.ScoreDto;
import com.huwdunnit.snookerupbackend.web.model.ScoreDtoList;
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
    ScoreService scoreService;

    @InjectMocks
    ScoreController scoreController;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(scoreController)
                .build();
    }

    @Test
    public void getAllScores_Should_ReturnAllScores() throws Exception {
        // Create variables
        ScoreDto score1 = new ScoreDto();
        score1.setScore(78);
        score1.setRoutineId(10L);
        score1.setPlayerId(20L);
        score1.setDateMade("2023-06-17T10:12:00");
        ScoreDto score2 = new ScoreDto();
        score2.setScore(120);
        score2.setRoutineId(11L);
        score2.setPlayerId(21L);
        score2.setDateMade("2023-06-17T10:23:00");
        List<ScoreDto> scores = List.of(score1, score2);
        ScoreDtoList scoreDtoList = new ScoreDtoList(scores);

        //Set mock expectations
        when(scoreService.listScores()).thenReturn(scoreDtoList);

        //Execute method under test
        mockMvc.perform(get("/api/v1/scores/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scores", hasSize(2)));
    }

    @Test
    public void getAllScoresBetweenDateRange_Should_ReturnOnlyScoresWithinDateRange() throws Exception {
        // Create variables
        ScoreDto score1 = new ScoreDto();
        score1.setScore(78);
        score1.setRoutineId(10L);
        score1.setPlayerId(20L);
        score1.setDateMade("2023-06-17T10:12:00");
        List<ScoreDto> scores = List.of(score1);
        ScoreDtoList scoreDtoList = new ScoreDtoList(scores);

        //Set mock expectations
        when(scoreService.findScoreByDateRange("2023-06-17T10:00:00", "2023-06-17T10:20:00")).thenReturn(scoreDtoList);

        //Execute method under test
        mockMvc.perform(get("/api/v1/scores?start=2023-06-17T10:00:00&end=2023-06-17T10:20:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scores", hasSize(1)));
    }

    @Test
    public void getScoreById_Should_ReturnCorrectScore_When_IdExists() throws Exception {
        // Create variables
        ScoreDto score1 = new ScoreDto();
        score1.setId(1L);
        score1.setScore(78);
        score1.setRoutineId(10L);
        score1.setPlayerId(11L);
        score1.setDateMade("2023-06-17T10:12:00");

        //Set mock expectations
        when(scoreService.findScoreById(1L)).thenReturn(score1);

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
        when(scoreService.findScoreById(700L)).thenThrow(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Score Not Found, ID: 700"));

        //Execute method under test
        mockMvc.perform(get("/api/v1/scores/700")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewScore_Should_ReturnNewScoreWithId() throws Exception {
        // Create variables
        ScoreDto score1 = new ScoreDto();
        score1.setScore(78);
        score1.setRoutineId(10L);
        score1.setPlayerId(20L);
        score1.setDateMade("2023-06-17T10:12:00");
        ScoreDto score1WithId = new ScoreDto();
        score1WithId.setScore(78);
        score1WithId.setRoutineId(10L);
        score1WithId.setPlayerId(11L);
        score1WithId.setId(1L);
        score1WithId.setDateMade("2023-06-17T10:12:00");

        //Set mock expectations
        when(scoreService.saveScore(score1)).thenReturn(score1WithId);

        //Execute method under test
        mockMvc.perform(post("/api/v1/scores/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(score1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }
}