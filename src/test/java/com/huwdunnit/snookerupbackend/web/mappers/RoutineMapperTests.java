package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.web.model.RoutineDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Mapstruct mapping of Routine objects, i.e. the RoutineMapper class.
 *
 * @author Huw
 */
public class RoutineMapperTests {

    RoutineMapper routineMapper = Mappers.getMapper(RoutineMapper.class);

    @Test
    public void routineToRoutineDto_Should_ReturnDtoWithCorrectValues() {
        //Create variables
        String title = "Title 1";
        String description = "Description 1";
        Routine routine = new Routine();
        routine.setId(1L);
        routine.setTitle(title);
        routine.setDescription(description);

        //Set mock expectations

        //Execute method under test
        RoutineDto routineDto = routineMapper.routineToRoutineDto(routine);
        assertEquals(title, routineDto.getTitle());
        assertEquals(description, routineDto.getDescription());
    }

    @Test
    public void routineDtoToRoutine_Should_ReturnRoutineWithCorrectValues() {
        //Create variables
        String title = "Title 1";
        String description = "Description 1";
        RoutineDto routineDto = new RoutineDto();
        routineDto.setId(1L);
        routineDto.setTitle(title);
        routineDto.setDescription(description);

        //Set mock expectations

        //Execute method under test
        Routine routine = routineMapper.routineDtoToRoutine(routineDto);
        assertEquals(title, routine.getTitle());
        assertEquals(description, routine.getDescription());
    }
}
