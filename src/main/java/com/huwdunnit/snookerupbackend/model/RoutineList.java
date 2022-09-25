package com.huwdunnit.snookerupbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Object to encapsulate a list of routines.
 * @author Huw
 */
@Data
@AllArgsConstructor
public class RoutineList {

    private List<Routine> routines;
}
