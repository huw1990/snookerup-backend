package com.huwdunnit.snookerupbackend.controllers.v1;

import com.huwdunnit.snookerupbackend.controllers.exceptions.ResourceNotFoundException;
import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.model.ScoreList;
import com.huwdunnit.snookerupbackend.repositories.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final ScoreRepository scoreRepository;

    @GetMapping
    public ScoreList getAllScores(){
        return new ScoreList(scoreRepository.findAll());
    }

    @GetMapping("{id}")
    public Score getScoreById(@PathVariable Long id){
        return scoreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Score createNewScore(@RequestBody Score score){
        return scoreRepository.save(score);
    }
}
