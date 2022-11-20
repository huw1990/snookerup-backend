package com.huwdunnit.snookerupbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Object to encapsulate a list of scores.
 * @author Huw
 */
@Data
@AllArgsConstructor
public class ScoreList {

    private List<Score> scores;
}
