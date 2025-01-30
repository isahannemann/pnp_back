package com.peaktech.pnp.model.defaults;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class DefaultEntity implements Serializable {

    @NotNull
    @Column(name = "activedPet")
    private Boolean activedPet;

    @PrePersist
    public void onCreatePet() {
        this.activedPet = Boolean.TRUE;
    }
}
