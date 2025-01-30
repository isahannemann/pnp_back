package com.peaktech.pnp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rolespet")
public class RolePet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_rolespet")
    @SequenceGenerator(sequenceName = "id_seq_rolespet", allocationSize = 1, name = "id_seq_rolespet")
    private Long id;

    @Size(max = 60)
    @NotNull
    @Column(name = "rolepet", length = 60)
    private String role;
}