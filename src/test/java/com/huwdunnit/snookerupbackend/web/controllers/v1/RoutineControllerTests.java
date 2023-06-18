package com.huwdunnit.snookerupbackend.web.controllers.v1;

import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.repositories.RoutineRepository;
import com.huwdunnit.snookerupbackend.services.RoutineService;
import com.huwdunnit.snookerupbackend.web.controllers.v1.RoutineController;
import com.huwdunnit.snookerupbackend.web.model.RoutineDto;
import com.huwdunnit.snookerupbackend.web.model.RoutineDtoList;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the RoutineController class.
 *
 * @author Huw
 */
public class RoutineControllerTests {

    @Mock
    RoutineService routineService;

    @InjectMocks
    RoutineController routineController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(routineController)
                .build();
    }

    @Test
    public void getAllRoutines_Should_ReturnAllRoutines() throws Exception {
        // Create variables
        RoutineDto routine1 = new RoutineDto();
        routine1.setId(1L);
        routine1.setTitle("Title 1");
        routine1.setDescription("Description 1");
        RoutineDto routine2 = new RoutineDto();
        routine2.setId(2L);
        routine2.setTitle("Title 2");
        routine2.setDescription("Description 2");
        List<RoutineDto> routines = List.of(routine1, routine2);
        RoutineDtoList routineDtoList = new RoutineDtoList(routines);

        //Set mock expectations
        when(routineService.listRoutines()).thenReturn(routineDtoList);

        //Execute method under test
        mockMvc.perform(get("/api/v1/routines/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.routines", hasSize(2)));
    }

    @Test
    public void getRoutineById_Should_ReturnCorrectRoutine_When_IdExists() throws Exception {
        // Create variables
        RoutineDto routine1 = new RoutineDto();
        routine1.setId(1L);
        routine1.setTitle("Title 1");
        routine1.setDescription("Description 1");

        //Set mock expectations
        when(routineService.findRoutineById(1L)).thenReturn(routine1);

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
        when(routineService.findRoutineById(700L)).thenThrow(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Routine Not Found, ID: 700"));

        //Execute method under test
        mockMvc.perform(get("/api/v1/routines/700")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
    }
}