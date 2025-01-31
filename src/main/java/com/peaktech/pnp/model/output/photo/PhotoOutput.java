package com.peaktech.pnp.model.output.photo;

import com.peaktech.pnp.model.defaults.DefaultEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
<<<<<<<< Updated upstream:src/main/java/com/peaktech/pnp/model/output/FotoTutorOutput.java
public class FotoTutorOutput extends DefaultEntityDTO {
    private String fotoBase64Tutor;
    private String formatoFotoTutor;
========
public class PhotoOutput extends DefaultEntityDTO {
    private String base64;
    private String format;
>>>>>>>> Stashed changes:src/main/java/com/peaktech/pnp/model/output/photo/PhotoOutput.java
}
