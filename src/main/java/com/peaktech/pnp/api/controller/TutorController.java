import com.peaktech.pnp.api.service.TutorService;
import com.peaktech.pnp.model.entity.Tutor;
import com.peaktech.pnp.model.input.TutorInput;
import com.peaktech.pnp.model.output.TutorOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("tutor")
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TutorInput tutorInput) {
        // Verificando se o email já foi cadastrado
        if (tutorService.findByEmail(tutorInput.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email já cadastrado"));
        } else {
            Tutor createdTutor = tutorService.save(tutorInput);
            TutorOutput tutorOutput = new TutorOutput(createdTutor);
            return ResponseEntity.ok(tutorOutput);
        }
    }
}
