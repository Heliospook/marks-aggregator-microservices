package com.example.scoresservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ScoreID.class)
public class Score {
    private double score;
    @Id
    private long testId;
    private String testName;
    @Id
    private String userId;
    private String userName;
}
