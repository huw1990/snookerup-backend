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

        Routine theLineUpRoutine = new Routine();
        theLineUpRoutine.setTitle("The Line Up");
        theLineUpRoutine.setDescription("Arrange all reds in a line up the middle of the table, in line with the blue, " +
                "pink, and black spots.\nPot the balls in order (i.e. red, colour, red, and so on), trying to " +
                "make as high a break as possible.\nCan you clear the table?");

        Routine theTLineUpRoutine = new Routine();
        theTLineUpRoutine.setTitle("The T Line Up");
        theTLineUpRoutine.setDescription("Arrange the reds in three lines of five reds, first between pink and black, " +
                "then either side of the pink, to form a \"T\" shape.\nPot the balls in order (i.e. red, colour, red, " +
                "and so on), trying to make as high a break as possible.\nIn this routine, all reds are nearer the " +
                "pink and black, so this replicates what you might see in a match, more than the Line Up would.\n" +
                "Can you clear the table?");

        Routine theTenRedTLineUpRoutine = new Routine();
        theTenRedTLineUpRoutine.setTitle("The 10 Red T Line Up");
        theTenRedTLineUpRoutine.setDescription("A slight variation on the T Line Up. Put four reds in a line between pink " +
                "and black, then three either side of the pink, to make 10 reds in total.\nPot the balls in order " +
                "(i.e. red, colour, red, and so on), trying to make as high a break as possible.\nCan you clear the " +
                "table?");

        Routine theSevenRedTLineUpRoutine = new Routine();
        theSevenRedTLineUpRoutine.setTitle("The 7 Red T Line Up");
        theSevenRedTLineUpRoutine.setDescription("A slight variation on the T Line Up. Put three reds in a line between " +
                "pink and black, then two either side of the pink, to make 7 reds in total.\nPot the balls in order " +
                "(i.e. red, colour, red, and so on), trying to make as high a break as possible.\nCan you clear the " +
                "table?");

        Routine clearingTheColoursRoutine = new Routine();
        clearingTheColoursRoutine.setTitle("Clearing The Colours");
        clearingTheColoursRoutine.setDescription("Put all colours on their spots, then try to clear them in order, " +
                "i.e. yellow, green, brown, blue, pink, black.");

        Player player = new Player();
        player.setFirstName("Mark");
        player.setLastName("Williams");
        player.setEmail("mjw@example.com");

        Score score1 = new Score();
        score1.setRoutine(theLineUpRoutine);
        score1.setPlayer(player);
        score1.setScore(100);

        Score score2 = new Score();
        score2.setRoutine(theLineUpRoutine);
        score2.setPlayer(player);
        score2.setScore(60);

        routineRepository.save(theLineUpRoutine);
        routineRepository.save(theTLineUpRoutine);
        routineRepository.save(theTenRedTLineUpRoutine);
        routineRepository.save(theSevenRedTLineUpRoutine);
        routineRepository.save(clearingTheColoursRoutine);
        playerRepository.save(player);
        scoreRepository.save(score1);
        scoreRepository.save(score2);

        log.debug("Loaded bootstrap data, routines={} players={} score={}",
                routineRepository.count(),
                playerRepository.count(),
                scoreRepository.count());
    }
}
