package com.peaktech.pnp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rolestutor")
public class RoleTutor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_rolestutor")
    @SequenceGenerator(sequenceName = "id_seq_rolestutor", allocationSize = 1, name = "id_seq_rolestutor")
    private Long id;

    @Size(max = 60)
    @NotNull
    @Column(name = "roletutor", length = 60)
    private String role;
}
