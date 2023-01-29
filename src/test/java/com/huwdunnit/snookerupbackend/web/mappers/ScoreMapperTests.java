package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.web.model.ScoreDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Mapstruct mapping of Score objects, i.e. the ScoreMapper class.
 *
 * @author Huw
 */
public class ScoreMapperTests {

    ScoreMapper scoreMapper = Mappers.getMapper(ScoreMapper.class);

    @Test
    public void scoreToScoreDto_Should_ReturnDtoWithCorrectValues() {
        //Create variables
        int scoreValue = 50;
        Score score = new Score();
        score.setScore(scoreValue);

        //Set mock expectations

        //Execute method under test
        ScoreDto scoreDto = scoreMapper.scoreToScoreDto(score);
        assertEquals(scoreValue, scoreDto.getScore());
    }

    @Test
    public void scoreDtoToScore_Should_ReturnRoutineWithCorrectValues() {
        //Create variables
        int scoreValue = 50;
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScore(scoreValue);

        //Set mock expectations

        //Execute method under test
        Score score = scoreMapper.scoreDtoToScore(scoreDto);
        assertEquals(scoreValue, score.getScore());
    }
}
