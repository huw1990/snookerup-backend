package com.huwdunnit.snookerupbackend.services;

import com.huwdunnit.snookerupbackend.web.model.ScoreDto;
import com.huwdunnit.snookerupbackend.web.model.ScoreDtoList;

/**
 * Service for operations on scores.
 *
 * @author Huw
 */
public interface ScoreService {

    /**
     * Get all scores.
     * @return A list of score DTOs
     */
    ScoreDtoList listScores();

    /**
     * Find a score by its ID.
     * @param scoreId The ID of the score
     * @return A DTO for the score with the provided ID
     */
    ScoreDto findScoreById(Long scoreId);

    /**
     * Save a score.
     * @param scoreDto A DTO for the score to save
     * @return A DTO of the saved score (with extra ID after adding to the DB).
     */
    ScoreDto saveScore(ScoreDto scoreDto);

    /**
     * Find all scores between a date range.
     * @param startDate The start of the date range, in ISO 8601 format
     * @param endDate The end of the date range, in ISO 8601 format
     * @return All scores in the DB  within this range
     */
    ScoreDtoList findScoreByDateRange(String startDate, String endDate);
}
