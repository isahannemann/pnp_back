package com.peaktech.pnp.api.controller;

import com.peaktech.pnp.api.service.RoleTutorService;
import com.peaktech.pnp.api.service.TutorService;
import com.peaktech.pnp.model.entity.RoleTutor;
import com.peaktech.pnp.model.entity.Tutor;
import com.peaktech.pnp.model.input.RoleTutorInput;
import com.peaktech.pnp.model.output.RoleTutorOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role/tutor")
public class RoleTutorController {

    @Autowired
    private final RoleTutorService roleTutorService;

    // Criação de RoleTutor
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody RoleTutorInput roleTutorInput) {
        RoleTutor createdRoleTutor = roleTutorService.save(roleTutorInput);
        RoleTutorOutput roleTutorOutput = new RoleTutorOutput(createdRoleTutor);
        return ResponseEntity.ok(roleTutorOutput);
    }

    // Listar todos os RoleTutor
    @GetMapping
    public ResponseEntity<List<RoleTutorOutput>> listAll() {
        List<RoleTutor> roleTutors = roleTutorService.listAllRoleTutor();
        List<RoleTutorOutput> responseDTOS = roleTutors.stream()
                .map(RoleTutorOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @PostMapping("/role-tutor")
    public ResponseEntity<?> saveRoleTutor(@Valid @RequestBody RoleTutorInput roleTutorInput) {
        // Verifica se já existe um tutor com o email informado
        Optional<Tutor> existingTutor = TutorService.findByEmail(roleTutorInput.getEmail());
        if (existingTutor.isPresent()) {
            // Associa a role ao tutor
            RoleTutor roleTutor = roleTutorService.save(roleTutorInput);
            RoleTutorOutput roleTutorOutput = new RoleTutorOutput(roleTutor);
            return ResponseEntity.ok(roleTutorOutput);
        } else {
            return ResponseEntity.badRequest().body("Tutor não encontrado para o email informado.");
        }
    }

    // Atualizar RoleTutor por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid RoleTutorInput roleTutorInput) {
        RoleTutor updatedRoleTutor = roleTutorService.updateById(id, roleTutorInput);
        RoleTutorOutput roleTutorOutput = new RoleTutorOutput(updatedRoleTutor);
        return ResponseEntity.ok(roleTutorOutput);
    }

    // Desativar RoleTutor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateById(@PathVariable Long id) {
        roleTutorService.deactivateById(id);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"RoleTutor desativado com sucesso\"}");
    }

    // Obter RoleTutor por ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleTutorOutput> getById(@PathVariable Long id) {
        RoleTutor roleTutor = roleTutorService.findById(id);
        RoleTutorOutput roleTutorOutput = new RoleTutorOutput(roleTutor);
        return ResponseEntity.ok(roleTutorOutput);
    }

    // Autenticação por Email para RoleTutor
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateByEmail(@RequestBody String email) {
        boolean authenticated = roleTutorService.authenticateByEmail(email);
        if (authenticated) {
            return ResponseEntity.ok("{\"message\": \"Tutor autenticado com sucesso\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Email não encontrado\"}");
        }
    }
}
