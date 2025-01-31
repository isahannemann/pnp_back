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

public class TutorInput {

    private String name;
    private String email;
    private String password;
    private Long role;
    private Date birth;
    private String bio;
    private String userNameTutor;

    @Nullable
    private String photoTutor;

}

