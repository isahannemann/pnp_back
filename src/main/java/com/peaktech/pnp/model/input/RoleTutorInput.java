package com.peaktech.pnp.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleTutorInput {

    @Email(message = "Email inválido")
    @NotEmpty(message = "O campo email é obrigatório")
    private String email;

    @NotEmpty(message = "O campo role é obrigatório")
    private String role;

}