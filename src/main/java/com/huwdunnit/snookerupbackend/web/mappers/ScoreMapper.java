package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.web.model.ScoreDto;
import org.mapstruct.Mapper;

/**
 * Mapstruct mapper for moving between Score DB entities and DTOs.
 *
 * @author Huw
 */
@Mapper
public interface ScoreMapper {

    ScoreDto scoreToScoreDto(Score score);

    Score scoreDtoToScore(ScoreDto scoreDto);
}
