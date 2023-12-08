package com.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller.java
 *
 * @author Pavithra Venkatesh (pavithravenkatesh@nmsworks.co.in)
 * @module PACKAGE_NAME
 * @created Dec 06, 2023
 */
@RestController
@RequestMapping("/v1/voting/")
public class controller
{
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private VotingRepository votingRepository;

    @PostMapping("/candidateDetails")
    public ResponseEntity<Candidate> saveCandidateDetails(@RequestParam ("name") String name)
    {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setVote(0);
        Candidate savedCandidate = votingRepository.save(candidate);

        logger.info("Saved candidate = {}",savedCandidate);

        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }

    @PostMapping("/castVote")
    public ResponseEntity<Candidate> castVote(@RequestParam ("name") String name)
    {
        Candidate candidate = votingRepository.findByName(name);

        if(candidate == null)
        {
            logger.info("No candidate found for given name = {}",name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        int vote = candidate.getVote();

        int count = vote + 1;

        candidate.setVote(count);

        Candidate updatedCandidate = votingRepository.save(candidate);

        return new ResponseEntity<>(updatedCandidate, HttpStatus.ACCEPTED);
    }

    @PostMapping("/countVote")
    public ResponseEntity<Integer> countVote(@RequestParam ("name") String name)
    {
        Candidate candidate = votingRepository.findByName(name);

        if(candidate == null)
        {
            logger.info("No candidate found for given name = {}",name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        int count = candidate.getVote();

        logger.info("For name = {}, count = {}",name,count);

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/listVote")
    public ResponseEntity<List<Candidate>> listVote()
    {
        List<Candidate> candidateList = votingRepository.findAll();

        if(candidateList.isEmpty())
        {
            logger.info("Obtained candidate details from DB is empty = {}",candidateList);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("All candidates name vs vote ={}",candidateList);

        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

    @GetMapping("/getWinner")
    public ResponseEntity<String> getWinner()
    {
        List<Candidate> candidateList = votingRepository.findAll();

        if(candidateList.isEmpty())
        {
            logger.info("Obtained candidate details from DB is empty = {}",candidateList);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String,Integer> map = new HashMap<>();

        for (Candidate candidate : candidateList) {

           map.put(candidate.getName(),candidate.getVote());
        }

        Integer max = Collections.max(map.values());

        String name = "";

        for(Map.Entry<String,Integer> entry:map.entrySet())
        {
            if(entry.getValue() == max)
            {
               name = entry.getKey();
            }
        };

        logger.info("winner = {}",name);

        return new ResponseEntity<>(name,HttpStatus.OK);
    }
}
