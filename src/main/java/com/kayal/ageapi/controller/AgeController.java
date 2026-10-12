package com.kayal.ageapi.controller;

import com.kayal.ageapi.dto.AgeRequest;
import com.kayal.ageapi.dto.AgeResponse;
import com.kayal.ageapi.service.AgeService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class AgeController {

    private final AgeService ageService;

    public AgeController(AgeService ageService) {
        this.ageService = ageService;
    }

    @GetMapping("/age")
    public AgeResponse getAgeByQuery(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
        return ageService.calculateAge(dateOfBirth);
    }

    @PostMapping("/age")
    public AgeResponse getAgeByBody(@Valid @RequestBody AgeRequest request) {
        return ageService.calculateAge(request.dateOfBirth());
    }
}
