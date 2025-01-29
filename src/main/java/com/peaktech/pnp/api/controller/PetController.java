package com.peaktech.pnp.api.controller;

import com.peaktech.pnp.api.service.PetService;
import com.peaktech.pnp.model.entity.Pet;
import com.peaktech.pnp.model.output.PetOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


