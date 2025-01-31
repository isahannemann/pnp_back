package com.peaktech.pnp.model.output.photo;

import com.peaktech.pnp.model.defaults.DefaultEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoOutput extends DefaultEntityDTO {
    private String base64;
    private String format;

    public PhotoOutput(String base64, String format) {
        this.base64 = base64;
        this.format = format;
    }
}
