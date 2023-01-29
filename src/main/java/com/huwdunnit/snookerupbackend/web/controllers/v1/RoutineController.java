package com.huwdunnit.snookerupbackend.web.controllers.v1;

import com.huwdunnit.snookerupbackend.services.RoutineService;
import com.huwdunnit.snookerupbackend.web.model.RoutineDto;
import com.huwdunnit.snookerupbackend.web.model.RoutineDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final RoutineService routineService;

    @GetMapping
    public ResponseEntity<RoutineDtoList> getAllRoutines(){
        RoutineDtoList routineList = routineService.listRoutines();
        return new ResponseEntity<>(routineList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoutineDto> getRoutineById(@PathVariable Long id){
        RoutineDto routineDto = routineService.findRoutineById(id);
        return new ResponseEntity<>(routineDto, HttpStatus.OK);
    }
}
