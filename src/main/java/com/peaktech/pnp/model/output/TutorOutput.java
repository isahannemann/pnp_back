package com.peaktech.pnp.model.output;

import com.peaktech.pnp.model.defaults.DefaultEntityDTO;
import com.peaktech.pnp.model.entity.Tutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorOutput extends DefaultEntityDTO {

    private String name;
    private String email;
    private String password;
    private Long idRole;
    private String roleTutor;
    private Date nascimento;
    private String bio;
    private String foto_base64_tutor;
    private String formato_foto_tutor;

    public TutorOutput(Tutor tutor) {
        this.setIdTutor(tutor.getIdTutor());
        this.name = tutor.getName();
        this.email = tutor.getEmail();
        this.password = tutor.getPassword();
        this.idRole = tutor.getRoleTutor().getIdTutor();
        this.roleTutor = tutor.getRoleTutor().getRoleTutor();
        this.nascimento = tutor.getNascimento();
        this.bio = tutor.getBio();

        if (tutor.getFoto() != null) {
            this.foto_base64_tutor = tutor.getFotoTutor();
            this.formato_foto_tutor = tutor.getFormatoFotoTutor();
        }

        this.setActivedTutor(pet.getActived());
    }
