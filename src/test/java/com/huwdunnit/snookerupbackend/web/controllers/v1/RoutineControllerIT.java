package com.huwdunnit.snookerupbackend.web.controllers.v1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for endpoints under the RoutineController class.
 *
 * @author Huwdunnit
 */
@WebMvcTest
public class RoutineControllerIT extends BaseIT {

    @Test
    void testGetAllRoutines() throws Exception{
        mockMvc.perform(get("/api/v1/routines" ))
                .andExpect(status().isOk());
    }

    @Test
    void testGetRoutineById() throws Exception{
        mockMvc.perform(get("/api/v1/routines/1" ))
                .andExpect(status().isOk());
    }
}
