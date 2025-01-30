package com.peaktech.pnp.model.output;

import com.peaktech.pnp.model.defaults.DefaultEntityDTO;
import com.peaktech.pnp.model.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetOutput extends DefaultEntityDTO {

    private String name;
    private LocalDate nascimento;
    private String tutor;
    private String banho;
    private String alimentacao;
    private String vacina;
    private String vermifugo;
    private String medicamento;
    private String obs;
    private String fotoBase64Pet;
    private String formatoFotoPet;

    public PetOutput(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("O objeto Pet não pode ser nulo.");
        }

        this.setIdPet(pet.getIdPet());
        this.name = pet.getName();
        this.nascimento = pet.getNascimento();
        this.tutor = pet.getTutor();
        this.banho = pet.getBanho();
        this.alimentacao = pet.getAlimentacao();
        this.vacina = pet.getVacina();
        this.vermifugo = pet.getVermifugo();
        this.medicamento = pet.getMedicamento();
        this.obs = pet.getObs();
        this.fotoBase64Pet = pet.getFotoPet() != null ? pet.getFotoPet() : "";
        this.formatoFotoPet = pet.getFormatoFotoPet() != null ? pet.getFormatoFotoPet() : "";
        this.setActivedPet(pet.getActivedPet());
    }
}
