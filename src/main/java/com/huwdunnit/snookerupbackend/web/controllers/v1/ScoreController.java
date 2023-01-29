package com.huwdunnit.snookerupbackend.web.controllers.v1;

import com.huwdunnit.snookerupbackend.services.ScoreService;
import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.web.model.ScoreDto;
import com.huwdunnit.snookerupbackend.web.model.ScoreDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for API requests related to scores.
 *
 * @author Huw
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping
    public ResponseEntity<ScoreDtoList> getAllScores(){
        ScoreDtoList scoreList = scoreService.listScores();
        return new ResponseEntity<>(scoreList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ScoreDto> getScoreById(@PathVariable Long id){
        ScoreDto scoreDto = scoreService.findScoreById(id);
        return new ResponseEntity<>(scoreDto, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScoreDto createNewScore(@RequestBody ScoreDto scoreDto){
        return scoreService.saveScore(scoreDto);
    }
}
