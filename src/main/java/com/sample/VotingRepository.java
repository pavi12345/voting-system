package com.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * VotingRepository.java
 *
 * @author Pavithra Venkatesh (pavithravenkatesh@nmsworks.co.in)
 * @module PACKAGE_NAME
 * @created Dec 06, 2023
 */
@Repository
public interface VotingRepository extends JpaRepository<Candidate,Integer>
{
   Candidate  findByName(String name);
}
