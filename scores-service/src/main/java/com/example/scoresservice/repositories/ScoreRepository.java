package com.example.scoresservice.repositories;

import com.example.scoresservice.models.Score;
import com.example.scoresservice.models.ScoreID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, ScoreID> {
    List<Score> findAllByUserId(String userId);
    List<Score> findAllByTestId(long testId);
}
