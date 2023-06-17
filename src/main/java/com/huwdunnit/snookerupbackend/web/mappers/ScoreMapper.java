package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.Score;
import com.huwdunnit.snookerupbackend.web.model.ScoreDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapstruct mapper for moving between Score DB entities and DTOs.
 *
 * @author Huw
 */
@Mapper
public interface ScoreMapper {

    @Mapping(target="playerId", source="player.id")
    @Mapping(target="routineId", source="routine.id")
    ScoreDto scoreToScoreDto(Score score);

    @InheritInverseConfiguration
    Score scoreDtoToScore(ScoreDto scoreDto);
}
