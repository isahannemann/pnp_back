package com.peaktech.pnp.model.output;

import com.peaktech.pnp.model.defaults.DefaultEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FotoPetOutput extends DefaultEntityDTO {
    private String fotoBase64Pet;
    private String formatoFotoPet;
}
