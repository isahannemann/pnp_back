package com.peaktech.pnp.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PetInput {

<<<<<<< Updated upstream
    private Long id;
    private String name;
    private Date birth;
=======
<<<<<<< Updated upstream
    private String name;
    private Date nascimento;
=======
    private String petUsername;
    private String name;
    private LocalDate birth;
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    private String tutor;
    private String bath;
    private String feed;
    private String vaccine;
    private String deworm;
    private String medicine;
    private String observation;

    @Nullable
    private String PhotoPet;

}
