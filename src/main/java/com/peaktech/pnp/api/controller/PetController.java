package com.peaktech.pnp.api.controller;

import com.peaktech.pnp.api.service.PetService;
import com.peaktech.pnp.model.entity.Pet;
import com.peaktech.pnp.model.input.PetInput;
import com.peaktech.pnp.model.output.PetOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")

public class PetController {

    @Autowired
    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetOutput>> listAll() {
        List<Pet> users = petService.listAll();
        List<PetOutput> responseDTOS = users.stream()
                .map(PetOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @PostMapping("/pet")
    public ResponseEntity<?> savePet(@Valid @RequestBody PetInput petInput) {
        if (petService.findById(petInput.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("Pet já cadastrado");
        } else {
            Pet createdPet = petService.save(petInput);
            PetOutput petOutput = new PetOutput(createdPet);

            return ResponseEntity.ok(petOutput);
        }
    }

}


