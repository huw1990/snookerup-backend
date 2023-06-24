package com.huwdunnit.snookerupbackend.web.controllers.v1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for endpoints under the ScoreController class.
 *
 * @author Huwdunnit
 */
@SpringBootTest
public class ScoreControllerIT extends BaseIT {

    @Test
    void testGetAllScores() throws Exception{
        mockMvc.perform(get("/api/v1/scores").with(httpBasic("mjw", "snooker")))
                .andExpect(status().isOk());
    }
}
