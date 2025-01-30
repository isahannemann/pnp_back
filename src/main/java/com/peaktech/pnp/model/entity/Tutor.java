package com.peaktech.pnp.model.entity;

import com.peaktech.pnp.model.defaults.DefaultEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "tutores")

public class Tutor extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_tutores")
    @SequenceGenerator(sequenceName = "id_seq_tutores", allocationSize = 1, name = "id_seq_tutores")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", length = 255)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", length = 255)
    private String password;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    private RoleTutor role;

    @Column(name = "nascimento")
    private Date nascimento;

    @Size(max = 255)
    @Column(name = "bio", length = 255)
    private String bio;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Transient
    private String formato_foto;

    public boolean isAdmin(){
        if(this.role.getRole().equals("ADMIN")){
            return true;
        }else{
            return false;
        }
    }

}
