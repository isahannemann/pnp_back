package com.peaktech.pnp.api.controller;

import com.peaktech.pnp.api.service.TutorService;
import com.peaktech.pnp.model.entity.Tutor;
import com.peaktech.pnp.model.input.TutorInput;
import com.peaktech.pnp.model.output.TutorOutput;
import com.peaktech.pnp.response.ErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("tutor")
public class TutorController {

    private final TutorService tutorService;

    // Lista todos os tutores
    @GetMapping
    public ResponseEntity<List<TutorOutput>> listAll() {
        List<Tutor> tutors = tutorService.listAll();
        List<TutorOutput> responseDTOS = tutors.stream()
                .map(TutorOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    // Cadastro de tutor
    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TutorInput tutorInput) {
        if (tutorService.findByEmail(tutorInput.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        // Verificando se o email já foi cadastrado
        if (tutorService.findByEmail(tutorInput.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email já cadastrado"));
        } else {
            Tutor createdTutor = tutorService.save(tutorInput);
            TutorOutput tutorOutput = new TutorOutput(createdTutor);
            return ResponseEntity.ok(tutorOutput);
        }
    }

    // Desativar pet por ID (Este método assume que o Tutor tem pets associados)
    @PutMapping("/deactivatePet/{petId}")
    public ResponseEntity<?> deactivatePet(@PathVariable Long petId) {
        Optional<Tutor> tutorOptional = tutorService.findByPetId(petId); // Supondo que você tenha um método para encontrar o tutor pelo petId
        if (tutorOptional.isPresent()) {
            Tutor tutor = tutorOptional.get();
            // Suponha que você tem um método para desativar o pet
            tutor.deactivatePet(petId);
            tutorService.save(tutor); // Atualiza o tutor com o pet desativado
            return ResponseEntity.ok(new TutorOutput(tutor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar pet por ID (Este método assume que o Tutor tem pets associados)
    @PutMapping("/updatePet/{petId}")
    public ResponseEntity<?> updatePet(@PathVariable Long petId, @Valid @RequestBody PetInput petInput) {
        Optional<Tutor> tutorOptional = tutorService.findByPetId(petId);
        if (tutorOptional.isPresent()) {
            Tutor tutor = tutorOptional.get();
            tutor.updatePet(petId, petInput);
            tutorService.save(tutor);
            return ResponseEntity.ok(new TutorOutput(tutor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Exibir perfil do tutor
    @GetMapping("/myProfile/{id}")
    public ResponseEntity<?> myProfile(@PathVariable Long id) {
        Optional<Tutor> tutorOptional = tutorService.findById(id);
        if (tutorOptional.isPresent()) {
            return ResponseEntity.ok(new TutorOutput(tutorOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
            Tutor createdTutor = tutorService.save(tutorInput);
            TutorOutput tutorOutput = new TutorOutput(createdTutor);
            return ResponseEntity.ok(tutorOutput);
        }
    }

    // Editar perfil do tutor
    @PutMapping("/editProfile/{id}")
    public ResponseEntity<?> editProfile(@PathVariable Long id, @Valid @RequestBody TutorInput tutorInput) {
        Optional<Tutor> tutorOptional = tutorService.findById(id);
        if (tutorOptional.isPresent()) {
            Tutor tutor = tutorOptional.get();
            // Atualiza os dados do tutor com as informações do TutorInput
            tutor.setName(tutorInput.getName());
            tutor.setEmail(tutorInput.getEmail());
            tutorService.save(tutor);
            return ResponseEntity.ok(new TutorOutput(tutor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
