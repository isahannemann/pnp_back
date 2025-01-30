package com.peaktech.pnp.model.defaults;

import lombok.Data;
import java.io.Serializable;

@Data
public abstract class DefaultEntityDTO implements Serializable {
    private Long idPet;
    private Long idTutor;
    private Boolean activedPet;
    }
}
