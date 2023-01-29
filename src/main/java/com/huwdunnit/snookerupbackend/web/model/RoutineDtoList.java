package com.huwdunnit.snookerupbackend.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Object to encapsulate a list of routine DTOs.
 * @author Huw
 */
@Data
@AllArgsConstructor
public class RoutineDtoList {

    private List<RoutineDto> routines;
}
