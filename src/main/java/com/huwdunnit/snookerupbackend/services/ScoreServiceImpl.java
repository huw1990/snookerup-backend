package com.huwdunnit.snookerupbackend.services;

import com.huwdunnit.snookerupbackend.model.security.User;
import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.repositories.security.UserRepository;
import com.huwdunnit.snookerupbackend.repositories.RoutineRepository;
import com.huwdunnit.snookerupbackend.repositories.ScoreRepository;
import com.huwdunnit.snookerupbackend.web.mappers.ScoreMapper;
import com.huwdunnit.snookerupbackend.web.model.ScoreDto;
import com.huwdunnit.snookerupbackend.web.model.ScoreDtoList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the score service.
 *
 * @author Huw
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ScoreServiceImpl implements ScoreService {

    /** Repository for DB operations related to scores. */
    private final ScoreRepository scoreRepository;

    /** Repository for DB operations related to users (i.e. the player that posted the score). */
    private final UserRepository userRepository;

    /** Repository for DB operations related to routines (i.e. the routine the score was achieved on). */
    private final RoutineRepository routineRepository;

    /** Mapper for going between DB entities and DTOs. */
    private final ScoreMapper scoreMapper;

    @Override
    public ScoreDtoList listScores() {
        log.debug("listScores");
        List<Score> scores = scoreRepository.findAll();
        return convertScoresListToDtos(scores);
    }

    @Override
    public ScoreDtoList findScoreByDateRange(String startDate, String endDate) {
        log.debug("findScoreByDateRange startDate={} endDate={}", startDate, endDate);
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<Score> scores = scoreRepository.findByDateMadeBetween(start, end);
        return convertScoresListToDtos(scores);
    }

    @Override
    public ScoreDto findScoreById(Long scoreId) {
        log.debug("findScoreById, scoreId={}", scoreId);
        Score score = scoreRepository.findById(scoreId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Score Not Found, ID: " + scoreId));
        log.debug("Found score={}", score);
        return scoreMapper.scoreToScoreDto(score);
    }

    @Override
    public ScoreDto saveScore(ScoreDto scoreDto) {
        log.debug("saveScore scoreDto={}", scoreDto);
        //Lookup linked objects from IDs
        Routine routine = routineRepository.findById(scoreDto.getRoutineId())
                .orElseThrow(() -> new RuntimeException("Routine not found"));
        User player = userRepository.findById(scoreDto.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));

        // Convert to proper score from DTO
        Score score = scoreMapper.scoreDtoToScore(scoreDto);
        // Don't allow the ID to be set by the client
        score.setId(null);
        // Set the full related objects
        score.setRoutine(routine);
        score.setPlayer(player);

        // Save the score in the DB
        Score savedScore = scoreRepository.save(score);
        log.debug("Saved score={}", savedScore);
        return scoreMapper.scoreToScoreDto(savedScore);
    }

    private ScoreDtoList convertScoresListToDtos(List<Score> scores) {
        log.debug("Number of scores={}", scores.size());
        List <ScoreDto> scoreDtos = scores.stream()
                .map(scoreMapper::scoreToScoreDto)
                .collect(Collectors.toList());
        return new ScoreDtoList(scoreDtos);
    }
}
