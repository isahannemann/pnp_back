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

    @Column(name = "nascimento")
    private Date nascimento;

    @Size(max = 255)
    @Column(name = "tutor")
    private String tutor;

    @Size(max = 255)
    @Column(name = "fotoPet")
    private String fotoPet;

    @Transient
    private String formatoFotoPet;

    @Size(max = 255)
    @Column(name = "alimentacao", length = 255)
    private String alimentacao;

    @Size(max = 255)
    @Column(name = "banho", length = 255)
    private String banho;

    @Size(max = 255)
    @Column(name = "vacina", length = 255)
    private String vacina;

    @Size(max = 255)
    @Column(name = "vermifugo", length = 255)
    private String vermifugo;

    @Size(max = 255)
    @Column(name = "medicamento", length = 255)
    private String medicamento;

    @Size(max = 255)
    @Column(name = "obs", length = 255)
    private String obs;

    @Size(max = 255)
    @Column(name = "activedPet", length = 255)
    private String activedPet;

}
