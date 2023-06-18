package com.huwdunnit.snookerupbackend.web.controllers.v1;

import com.huwdunnit.snookerupbackend.repositories.PlayerRepository;
import com.huwdunnit.snookerupbackend.repositories.RoutineRepository;
import com.huwdunnit.snookerupbackend.repositories.ScoreRepository;
import com.huwdunnit.snookerupbackend.services.PlayerService;
import com.huwdunnit.snookerupbackend.services.RoutineService;
import com.huwdunnit.snookerupbackend.services.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Common functionality for all integration tests.
 *
 * @author Huwdunnit
 */
public abstract class BaseIT {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @MockBean
    PlayerRepository playerRepository;

    @MockBean
    RoutineRepository routineRepository;

    @MockBean
    ScoreRepository scoreRepository;

    @MockBean
    PlayerService playerService;

    @MockBean
    RoutineService routineService;

    @MockBean
    ScoreService scoreService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
}
