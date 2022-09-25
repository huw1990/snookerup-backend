package com.huwdunnit.snookerupbackend.controllers.v1;

import com.huwdunnit.snookerupbackend.controllers.exceptions.RoutineNotFoundException;
import com.huwdunnit.snookerupbackend.model.Routine;
import com.huwdunnit.snookerupbackend.model.RoutineList;
import com.huwdunnit.snookerupbackend.repositories.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for API requests related to routines.
 *
 * @author Huw
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines")
public class RoutineController {

    private final RoutineRepository routineRepository;

    @GetMapping
    public RoutineList getAllRoutines(){
        return new RoutineList(routineRepository.findAll());
    }

    @GetMapping("{id}")
    public Routine getRoutineById(@PathVariable Long id){
        return routineRepository.findById(id).orElseThrow(() -> new RoutineNotFoundException(id));
    }
}
