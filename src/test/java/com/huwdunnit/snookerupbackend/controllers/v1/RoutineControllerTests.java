package com.huwdunnit.snookerupbackend.controllers.v1;

import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.repositories.RoutineRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the RoutineController class.
 *
 * @author Huw
 */
public class RoutineControllerTests {

    @Mock
    RoutineRepository routineRepository;

    @Mock
    ResourceNotFoundAdvice resourceNotFoundAdvice;

    @InjectMocks
    RoutineController routineController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(routineController)
                .setControllerAdvice(resourceNotFoundAdvice)
                .build();
    }

    @Test
    public void getAllRoutines_Should_ReturnAllRoutines() throws Exception {
        // Create variables
        Routine routine1 = new Routine();
        routine1.setId(1L);
        routine1.setTitle("Title 1");
        routine1.setDescription("Description 1");
        Routine routine2 = new Routine();
        routine2.setId(2L);
        routine2.setTitle("Title 2");
        routine2.setDescription("Description 2");
        List<Routine> routines = List.of(routine1, routine2);

        //Set mock expectations
        when(routineRepository.findAll()).thenReturn(routines);

        //Execute method under test
        mockMvc.perform(get("/api/v1/routines/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.routines", hasSize(2)));
    }

    @Test
    public void getRoutineById_Should_ReturnCorrectRoutine_When_IdExists() throws Exception {
        // Create variables
        Routine routine1 = new Routine();
        routine1.setId(1L);
        routine1.setTitle("Title 1");
        routine1.setDescription("Description 1");

        //Set mock expectations
        when(routineRepository.findById(1L)).thenReturn(Optional.of(routine1));

        //Execute method under test
        mockMvc.perform(get("/api/v1/routines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void getRoutineById_Should_ReturnNotFound_When_IdDoesntExist() throws Exception {
        // Create variables

        //Set mock expectations
        when(routineRepository.findById(700L)).thenReturn(Optional.ofNullable(null));

        //Execute method under test
        mockMvc.perform(get("/api/v1/routines/700")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
    }
}