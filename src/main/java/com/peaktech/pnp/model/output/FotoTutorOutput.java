package com.peaktech.pnp.model.output;

import com.peaktech.pnp.model.defaults.DefaultEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FotoTutorOutput extends DefaultEntityDTO {
    private String fotoBase64Tutor;
    private String formatoFotoTutor;
}
