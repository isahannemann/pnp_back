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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetOutput>> listAll() {
        List<Pet> pets = petService.listAll();
        List<PetOutput> responseDTOS = pets.stream()
                .map(PetOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

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
}
