package com.peaktech.pnp.model.entity;

import com.peaktech.pnp.model.defaults.DefaultEntity;
<<<<<<< Updated upstream
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
=======
<<<<<<< Updated upstream
import com.sun.istack.internal.NotNull;
>>>>>>> Stashed changes
import jakarta.persistence.*;
=======
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
>>>>>>> Stashed changes
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "pets")
public class Pet extends DefaultEntity implements Serializable {

    @Id
<<<<<<< Updated upstream
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_pets")
    @SequenceGenerator(sequenceName = "id_seq_pets", allocationSize = 1, name = "id_seq_pets")
    private Long idPet;
=======
<<<<<<< Updated upstream
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_pet")
    @SequenceGenerator(sequenceName = "id_seq_pet", allocationSize = 1, name = "id_seq_pet")
    private Long id;
=======
    @Column(name = "userNamePet", unique = true)
    private String usernamePet;
>>>>>>> Stashed changes
>>>>>>> Stashed changes

    @Size(max = 255)
    @NotNull
    @Column(name = "name")
    private String name;

<<<<<<< Updated upstream
    @Column(name = "birth")
    private Date birth;
=======
<<<<<<< Updated upstream
    @Column(name = "nascimento")
    private Date nascimento;
=======
    @Column(name = "birth")
    private LocalDate birth;
>>>>>>> Stashed changes
>>>>>>> Stashed changes

    @Size(max = 255)
    @Column(name = "tutor")
    private String tutor;

    @Size(max = 255)
    @Column(name = "photoPet")
    private String photoPet;

    @Transient
    private String formatPhotoPet;

    @Size(max = 255)
    @Column(name = "feed", length = 255)
    private String feed;

    @Size(max = 255)
    @Column(name = "bath", length = 255)
    private String bath;

    @Size(max = 255)
    @Column(name = "vaccinate", length = 255)
    private String vaccinate;

    @Size(max = 255)
    @Column(name = "deworm", length = 255)
    private String deworm;

    @Size(max = 255)
    @Column(name = "medicine", length = 255)
    private String medicine;

    @Size(max = 255)
<<<<<<< Updated upstream
    @Column(name = "observation", length = 255)
    private String observation;

    @Size(max = 255)
    @Column(name = "activedPet", length = 255)
    private String activedPet;
=======
<<<<<<< Updated upstream
    @Column(name = "obs", length = 255)
    private String obs;
>>>>>>> Stashed changes

=======
    @Column(name = "observation", length = 255)
    private String observation;

    @Size(max = 255)
    @Column(name = "activedPet", length = 255)
    private String activedPet;
>>>>>>> Stashed changes
}
