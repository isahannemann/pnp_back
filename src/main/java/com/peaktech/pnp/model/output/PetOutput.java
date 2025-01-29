package com.peaktech.pnp.model.output;

import com.peaktech.pnp.model.defaults.DefaultEntityDTO;
import com.peaktech.pnp.model.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetOutput extends DefaultEntityDTO {

    private String name;
    private Date nascimento;
    private String tutor;
    private String banho;
    private String alimentacao;
    private String vacina;
    private String vermifugo;
    private String medicamento;
    private String obs;
    private String foto_base64;
    private String formato_foto;

    public PetOutput(Pet pet) {
        this.setId(pet.getId());
        this.name = pet.getName();
        this.nascimento = pet.getNascimento();
        this.tutor = pet.getTutor();
        this.banho = pet.getBanho();
        this.alimentacao = pet.getAlimentacao();
        this.vacina = pet.getVacina();
        this.vermifugo = pet.getVermifugo();
        this.medicamento = pet.getMedicamento();
        this.obs = pet.getObs();


        if (pet.getFoto() != null) {
            this.foto_base64 = pet.getFoto();
            this.formato_foto = pet.getFormato_foto();
        }

        this.setActived(pet.getActived());
    }
}