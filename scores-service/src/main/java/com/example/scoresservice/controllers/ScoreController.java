package com.example.scoresservice.controllers;

import com.example.scoresservice.models.Score;
import com.example.scoresservice.models.ScoreID;
import com.example.scoresservice.proxies.DataProviderProxy;
import com.example.scoresservice.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scores-service/v1/scores")
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private DataProviderProxy proxy;
    @PostMapping
    public Score addScore(@RequestBody Score score){
        return scoreRepository.save(score);
    }

    @PostMapping("/ids")
    public Score addScoreByIds(@RequestBody Score score, @RequestHeader("x-RollNumber") String rollNumber){
        if(!score.getUserId().equals(rollNumber)){
            throw new RuntimeException("Unauthorized access to resource");
        }
        String userName = proxy.retrieveUserName(score.getUserId());
        String testName = proxy.retrieveTestName(score.getTestId());
        score.setUserName(userName);
        score.setTestName(testName);
        return scoreRepository.save(score);
    }

    @GetMapping("/user/{id}")
    public List<Score> getScoresByUser(@PathVariable String id, @RequestHeader("x-RollNumber") String rollNumber){
        if(!id.equals(rollNumber)){
            throw new RuntimeException("Unauthorized access to resource");
        }
        return scoreRepository.findAllByUserId(id);
    }

    @GetMapping("/test/{id}")
    public List<Score> getScoresByTest(@PathVariable long id, @RequestHeader("x-RollNumber") String rollNumber){
        Optional<Score> userScore = scoreRepository.findById(new ScoreID(rollNumber, id));
        if(userScore.isEmpty()){
            throw new RuntimeException("User did not provide their own score");
        }
        return scoreRepository.findAllByTestId(id);
    }
}
