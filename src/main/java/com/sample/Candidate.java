package com.sample;

import javax.persistence.*;

/**
 * Candidate.java
 *
 * @author Pavithra Venkatesh (pavithravenkatesh@nmsworks.co.in)
 * @module PACKAGE_NAME
 * @created Dec 06, 2023
 */
@Entity
public class Candidate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private int vote;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", vote=" + vote +
                '}';
    }
}
