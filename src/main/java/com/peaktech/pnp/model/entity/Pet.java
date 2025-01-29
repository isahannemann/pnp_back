package com.peaktech.pnp.model.entity;

import com.peaktech.pnp.model.defaults.DefaultEntity;
import com.sun.istack.internal.NotNull;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_pet")
    @SequenceGenerator(sequenceName = "id_seq_pet", allocationSize = 1, name = "id_seq_pet")
    private Long id;

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
    @Column(name = "foto")
    private String foto;

    @Transient
    private String formato_foto;

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

}
