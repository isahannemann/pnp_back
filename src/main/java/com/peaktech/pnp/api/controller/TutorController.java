package com.peaktech.pnp.api.controller;

import com.peaktech.pnp.api.service.TutorService;
import com.peaktech.pnp.model.entity.Tutor;
import com.peaktech.pnp.model.input.TutorInput;
import com.peaktech.pnp.model.output.TutorOutput;
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
    public ResponseEntity<?> save(@Valid @RequestBody TutorInput tutorInput) {
        if (TutorService.findByEmail(tutorInput.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        } else {
            Tutor createdTutor = TutorService.save(tutorInput);
            TutorOutput userOutput = new TutorOutput(createdTutor);
            return ResponseEntity.ok(userOutput);
        }
    }

}
