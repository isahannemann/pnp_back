package com.peaktech.pnp.api.service.upload;

import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.photo.PhotoOutput;

import java.io.IOException;

public interface UploadStrategy {
    String saveFile(FileUploadInput request);
    PhotoOutput getFile(String filename) throws IOException;
    boolean deleteFile(String filename);
}
