package com.kayal.ageapi.dto;

import java.time.LocalDate;

public record AgeResponse(
        LocalDate dateOfBirth,
        int age
) {
}
