package com.kayal.ageapi.service;

import com.kayal.ageapi.dto.AgeResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AgeService {

    public AgeResponse calculateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }

        LocalDate today = LocalDate.now();

        if (dateOfBirth.isAfter(today)) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        int age = Period.between(dateOfBirth, today).getYears();

        return new AgeResponse(dateOfBirth, age);
    }
}
