import com.peaktech.pnp.api.service.PetService;
import com.peaktech.pnp.model.entity.Pet;
import com.peaktech.pnp.model.input.PetInput;
import com.peaktech.pnp.model.output.PetOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    // Lista todos os pets
    @GetMapping
    public ResponseEntity<List<PetOutput>> listAll() {
        List<Pet> pets = petService.listAll();
        List<PetOutput> responseDTOS = pets.stream()
                .map(PetOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    // Cadastro de pet
    @PostMapping("/pet")
    public ResponseEntity<?> savePet(@Valid @RequestBody PetInput petInput) {
        if (petService.findById(petInput.getId()).isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Pet já cadastrado"));
        } else {
            Pet createdPet = petService.save(petInput);
            PetOutput petOutput = new PetOutput(createdPet);
            return ResponseEntity.ok(petOutput);
        }
    }

    // Desativar pet por ID
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivatePet(@PathVariable Long id) {
        Optional<Pet> petOptional = petService.findById(id);
        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            pet.setActive(false); // Define o pet como inativo
            petService.save(pet); // Salva o pet desativado
            return ResponseEntity.ok(new PetOutput(pet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar pet por ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePet(@PathVariable Long id, @Valid @RequestBody PetInput petInput) {
        Optional<Pet> petOptional = petService.findById(id);
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

    // Exibir perfil do pet (aqui você pode adicionar lógica para retornar detalhes do pet)
    @GetMapping("/myprofile/{id}")
    public ResponseEntity<?> myProfile(@PathVariable Long id) {
        Optional<Pet> petOptional = petService.findById(id);
        if (petOptional.isPresent()) {
            return ResponseEntity.ok(new PetOutput(petOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Editar perfil do pet (aqui você pode adicionar lógica para editar o perfil)
    @PutMapping("/editprofile/{id}")
    public ResponseEntity<?> editProfile(@PathVariable Long id, @Valid @RequestBody PetInput petInput) {
        Optional<Pet> petOptional = petService.findById(id);
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
