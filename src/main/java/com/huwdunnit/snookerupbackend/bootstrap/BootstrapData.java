package com.huwdunnit.snookerupbackend.bootstrap;

import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.model.Player;
import com.huwdunnit.snookerupbackend.repositories.RoutineRepository;
import com.huwdunnit.snookerupbackend.repositories.ScoreRepository;
import com.huwdunnit.snookerupbackend.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Bootstraps some initial data into the DB.
 * @author Huw
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BootstrapData implements CommandLineRunner {

    private final RoutineRepository routineRepository;
    private final ScoreRepository scoreRepository;
    private final PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Loading bootstrap data...");

        Routine routine = new Routine();
        routine.setTitle("The Line Up");
        routine.setDescription("Arrange all reds in a line up the middle of the table, in line with the blue, " +
                "pink, and black spots.\n Pot the balls in order (i.e. red, colour, red, and so on), trying to " +
                "make as high a break as possible.\n Can you clear the table?");

        Player player = new Player();
        player.setFirstName("Mark");
        player.setLastName("Williams");
        player.setEmail("mjw@example.com");

        Score score1 = new Score();
        score1.setRoutine(routine);
        score1.setUser(player);
        score1.setScore(100);

        Score score2 = new Score();
        score2.setRoutine(routine);
        score2.setUser(player);
        score2.setScore(60);

        routineRepository.save(routine);
        playerRepository.save(player);
        scoreRepository.save(score1);
        scoreRepository.save(score2);

        log.debug("Loaded bootstrap data, routines={} players={} score={}",
                routineRepository.count(),
                playerRepository.count(),
                scoreRepository.count());
    }
}
