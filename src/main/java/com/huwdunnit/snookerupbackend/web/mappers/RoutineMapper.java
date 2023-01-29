package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.web.model.RoutineDto;
import org.mapstruct.Mapper;

/**
 * Mapstruct mapper for moving between Routine DB entities and DTOs.
 *
 * @author Huw
 */
@Mapper
public interface RoutineMapper {

    RoutineDto routineToRoutineDto(Routine routine);

    Routine routineDtoToRoutine(RoutineDto routineDto);
}
