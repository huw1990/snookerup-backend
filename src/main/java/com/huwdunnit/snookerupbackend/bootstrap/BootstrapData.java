package com.huwdunnit.snookerupbackend.bootstrap;

import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.model.security.Authority;
import com.huwdunnit.snookerupbackend.model.security.Role;
import com.huwdunnit.snookerupbackend.model.security.User;
import com.huwdunnit.snookerupbackend.repositories.RoutineRepository;
import com.huwdunnit.snookerupbackend.repositories.ScoreRepository;
import com.huwdunnit.snookerupbackend.repositories.security.AuthorityRepository;
import com.huwdunnit.snookerupbackend.repositories.security.RoleRepository;
import com.huwdunnit.snookerupbackend.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        log.debug("Loading bootstrap data...");

        // Permissions for operations on any score
        Authority createScore = authorityRepository.save(Authority.builder().permission("score.create").build());
        Authority updateScore = authorityRepository.save(Authority.builder().permission("score.update").build());
        Authority readScore = authorityRepository.save(Authority.builder().permission("score.read").build());
        Authority deleteScore = authorityRepository.save(Authority.builder().permission("score.delete").build());

        // Permissions for operations on scores for the same user
        Authority userCreateScore = authorityRepository.save(Authority.builder().permission("user.score.create").build());
        Authority userUpdateScore = authorityRepository.save(Authority.builder().permission("user.score.update").build());
        Authority userReadScore = authorityRepository.save(Authority.builder().permission("user.score.read").build());
        Authority userDeleteScore = authorityRepository.save(Authority.builder().permission("user.score.delete").build());

        Authority readUsers = authorityRepository.save(Authority.builder().permission("users.read").build());

        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());

        adminRole.setAuthorities(new HashSet<>(Set.of(createScore, updateScore, readScore, deleteScore,
                userCreateScore, userUpdateScore, userReadScore, userDeleteScore, readUsers)));
        userRole.setAuthorities(new HashSet<>(Set.of(userCreateScore, userUpdateScore, userReadScore, userDeleteScore)));

        roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        Routine theLineUpRoutine = routineRepository.save(Routine.builder()
                .title("The Line Up")
                .description("Arrange all reds in a line up the middle of the table, in line with the blue, " +
                        "pink, and black spots.\nPot the balls in order (i.e. red, colour, red, and so on), trying to " +
                        "make as high a break as possible.\nCan you clear the table?")
                .build());
        Routine theTLineUpRoutine = routineRepository.save(Routine.builder()
                .title("The T Line Up")
                .description("Arrange the reds in three lines of five reds, first between pink and black, " +
                        "then either side of the pink, to form a \"T\" shape.\nPot the balls in order (i.e. red, colour, red, " +
                        "and so on), trying to make as high a break as possible.\nIn this routine, all reds are nearer the " +
                        "pink and black, so this replicates what you might see in a match, more than the Line Up would.\n" +
                        "Can you clear the table?")
                .build());
        Routine theTenRedTLineUpRoutine = routineRepository.save(Routine.builder()
                .title("The 10 Red T Line Up")
                .description("A slight variation on the T Line Up. Put four reds in a line between pink " +
                        "and black, then three either side of the pink, to make 10 reds in total.\nPot the balls in order " +
                        "(i.e. red, colour, red, and so on), trying to make as high a break as possible.\nCan you clear the " +
                        "table?")
                .build());
        Routine theSevenRedTLineUpRoutine = routineRepository.save(Routine.builder()
                .title("The 7 Red T Line Up")
                .description("A slight variation on the T Line Up. Put three reds in a line between " +
                        "pink and black, then two either side of the pink, to make 7 reds in total.\nPot the balls in order " +
                        "(i.e. red, colour, red, and so on), trying to make as high a break as possible.\nCan you clear the " +
                        "table?")
                .build());
        Routine clearingTheColoursRoutine = routineRepository.save(Routine.builder()
                .title("Clearing the Colours")
                .description("Put all colours on their spots, then try to clear them in order, " +
                        "i.e. yellow, green, brown, blue, pink, black.")
                .build());

        User admin = userRepository.save(User.builder()
                .firstName("Huw")
                .lastName("Carpenter")
                .email("huw@example.com")
                .username("huw")
                .password(passwordEncoder.encode("password"))
                .role(adminRole)
                .build());

        User player1 = userRepository.save(User.builder()
                .firstName("Mark")
                .lastName("Williams")
                .email("mjw@example.com")
                .username("mjw")
                .password(passwordEncoder.encode("snooker"))
                .role(userRole)
                .build());

        User player2 = userRepository.save(User.builder()
                .firstName("Neil")
                .lastName("Robertson")
                .email("thunder@example.com")
                .username("neil")
                .password(passwordEncoder.encode("robbo"))
                .role(userRole)
                .build());

        Score score1 = scoreRepository.save(Score.builder()
                .routine(theLineUpRoutine)
                .player(player1)
                .score(100)
                // Set date to 10:12 on 17th June 2023
                .dateMade(LocalDateTime.of(2023, 6, 17, 10, 12, 0))
                .build());

        Score score2 = scoreRepository.save(Score.builder()
                .routine(theLineUpRoutine)
                .player(player1)
                .score(60)
                // Set date to 10:12 on 17th June 2023
                .dateMade(LocalDateTime.of(2023, 6, 17, 10, 12, 0))
                .build());

        Score score3 = scoreRepository.save(Score.builder()
                .routine(theTenRedTLineUpRoutine)
                .player(player2)
                .score(90)
                // Set date to 10:12 on 17th June 2023
                .dateMade(LocalDateTime.of(2023, 6, 18, 12, 10, 0))
                .build());

        log.debug("Loaded bootstrap data, routines={} users={} authorities={} scores={}",
                routineRepository.count(),
                userRepository.count(),
                authorityRepository.count(),
                scoreRepository.count());
    }
}
