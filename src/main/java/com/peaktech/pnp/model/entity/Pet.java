package com.peaktech.pnp.model.entity;

import com.peaktech.pnp.model.defaults.DefaultEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
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
@Table(name = "pets")

public class Pet extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_pets")
    @SequenceGenerator(sequenceName = "id_seq_pets", allocationSize = 1, name = "id_seq_pets")
    private Long idPet;

    @Size(max = 255)
    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private Date birth;

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
    @Column(name = "observation", length = 255)
    private String observation;

    @Size(max = 255)
    @Column(name = "activedPet", length = 255)
    private String activedPet;

}
