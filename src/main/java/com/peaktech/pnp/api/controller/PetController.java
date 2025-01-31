package com.peaktech.pnp.api.controller;

import com.peaktech.pnp.api.service.PetService;
import com.peaktech.pnp.model.entity.Pet;
import com.peaktech.pnp.model.output.PetOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
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

    // Cadastro de pet
    @PostMapping
    public ResponseEntity<?> savePet(@Valid @RequestBody PetInput petInput) {
        if (petService.findByName(petInput.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("Pet com esse nome já cadastrado");
        } else {
            Pet createdPet = petService.save(petInput);
            PetOutput petOutput = new PetOutput(createdPet);

            return ResponseEntity.ok(petOutput);
        }
    }

    // Desativar pet por nome de usuário
    @PutMapping("/deactivate/{name}")
    public ResponseEntity<?> deactivatePet(@PathVariable String name) {
        Optional<Pet> petOptional = petService.findByName(name);
        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            pet.setActive(false); // Define o pet como inativo
            petService.save(pet); // Salva o pet desativado
            return ResponseEntity.ok(new PetOutput(pet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar pet por nome de usuário
    @PutMapping("/update/{name}")
    public ResponseEntity<?> updatePet(@PathVariable String name, @Valid @RequestBody PetInput petInput) {
        Optional<Pet> petOptional = petService.findByName(name);
        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            // Atualiza os dados do pet com as informações do PetInput
            pet.setName(petInput.getName());
            pet.setBreed(petInput.getBreed());
            pet.setAge(petInput.getAge());
            pet.setActive(petInput.isActive()); // Atualiza o status ativo
            petService.save(pet);
            return ResponseEntity.ok(new PetOutput(pet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Exibir perfil do pet pelo nome de usuário
    @GetMapping("/myprofile/{name}")
    public ResponseEntity<?> myProfile(@PathVariable String name) {
        Optional<Pet> petOptional = petService.findByName(name);
        if (petOptional.isPresent()) {
            return ResponseEntity.ok(new PetOutput(petOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Editar perfil do pet pelo nome de usuário
    @PutMapping("/editprofile/{name}")
    public ResponseEntity<?> editProfile(@PathVariable String name, @Valid @RequestBody PetInput petInput) {
        Optional<Pet> petOptional = petService.findByName(name);
        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            // Atualiza os dados do pet com as informações do PetInput
            pet.setName(petInput.getName());
            pet.setBreed(petInput.getBreed());
            pet.setAge(petInput.getAge());
            pet.setActive(petInput.isActive()); // Atualiza o status ativo
            petService.save(pet);
            return ResponseEntity.ok(new PetOutput(pet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


