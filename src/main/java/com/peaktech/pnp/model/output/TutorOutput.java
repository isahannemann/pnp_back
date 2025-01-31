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
    private String userNameTutor;
    private String roleTutor;
    private Date birth;
    private String bio;
    private String fotoBase64Tutor;
    private String formatoPhotoTutor;

    public TutorOutput(Tutor tutor) {
        this.setIdTutor(tutor.getIdTutor());
        this.name = tutor.getName();
        this.email = tutor.getEmail();
        this.password = tutor.getPassword();
        this.userNameTutor = tutor.getUserNameTutor();
        this.roleTutor = tutor.getRoleTutor().getRoleTutor();
        this.birth = tutor.getBirth();
        this.bio = tutor.getBio();

        if (tutor.getFoto() != null) {
            this.fotoBase64Tutor = tutor.getPhotoTutor();
            this.formatoPhotoTutor = tutor.getFormatPhotoTutor();
        }
    }
}
