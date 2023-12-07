package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * SpringBootMainApplication.java
 *
 * @author Pavithra Venkatesh (pavithravenkatesh@nmsworks.co.in)
 * @module PACKAGE_NAME
 * @created Dec 07, 2023
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.sample"})
public class SpringBootMainApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootMainApplication.class);
    }
}
