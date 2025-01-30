package com.peaktech.pnp.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PetInput {

    private String name;
    private Date nascimento;
    private String tutor;
    private String banho;
    private String alimentacao;
    private String vacina;
    private String vermifugo;
    private String medicamento;
    private String obs;


    @Nullable

    private String fotoPet;


}
