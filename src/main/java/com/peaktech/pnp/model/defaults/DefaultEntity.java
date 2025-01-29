package com.peaktech.pnp.model.defaults;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class DefaultEntity implements Serializable {
    @NotNull
    @Column(name = "actived")
    private Boolean actived;

    @PrePersist
    public void onCreate() {
        this.actived = Boolean.TRUE;
    }
}
