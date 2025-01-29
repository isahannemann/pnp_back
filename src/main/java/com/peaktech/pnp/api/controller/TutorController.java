package com.peaktech.pnp.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("tutor")
public class TutorController {

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody tutorInput tutorInput) {
        if (tutorService.findByEmail(tutorInput.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");

        } else if (tutorInput.getCpf() != null && tutorService.findByCpf(tutorInput.getCpf()).isPresent()) {
            return ResponseEntity.badRequest().body("Cpf já cadastrado");

        } else {
            Tutor createdTutor = TutorService.save(tutorInput);
            TutorOutput userOutput = new TutorOutput(createdTutor);
            return ResponseEntity.ok(userOutput);
        }
    }

}
