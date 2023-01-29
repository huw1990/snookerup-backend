package com.huwdunnit.snookerupbackend.services;

import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.repositories.RoutineRepository;
import com.huwdunnit.snookerupbackend.web.mappers.RoutineMapper;
import com.huwdunnit.snookerupbackend.web.model.RoutineDto;
import com.huwdunnit.snookerupbackend.web.model.RoutineDtoList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the routine service.
 *
 * @author Huw
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RoutineServiceImpl implements RoutineService {

    /** Repository for DB operations related to routines. */
    private final RoutineRepository routineRepository;

    /** Mapper for going between DB entities and DTOs. */
    private final RoutineMapper routineMapper;

    @Override
    public RoutineDtoList listRoutines() {
        log.debug("listRoutines");
        List<Routine> routines = routineRepository.findAll();
        log.debug("Number of routines={}", routines.size());
        List <RoutineDto> routineDtos = routines.stream()
                .map(routineMapper::routineToRoutineDto)
                .collect(Collectors.toList());
        return new RoutineDtoList(routineDtos);
    }

    @Override
    public RoutineDto findRoutineById(Long routineId) {
        log.debug("findRoutineById, routineId={}", routineId);
        Routine routine = routineRepository.findById(routineId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Routine Not Found, ID: " + routineId));
        log.debug("Found routine={}", routine);
        return routineMapper.routineToRoutineDto(routine);
    }
}
