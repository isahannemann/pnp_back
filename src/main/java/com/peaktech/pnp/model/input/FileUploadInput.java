package com.peaktech.pnp.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class FileUploadInput {
    private String fileName;
    private String pdfBase64;
}
