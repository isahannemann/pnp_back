package com.peaktech.pnp.api.service;

import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.PhotoOutput;

public interface UploadStrategy {
    String saveFile(FileUploadInput request);
    PhotoOutput getFile(String filename) throws IOException;
    boolean deleteFile(String filename);
}
