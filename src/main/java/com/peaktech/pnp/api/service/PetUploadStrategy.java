package com.peaktech.pnp.api.service;

import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.PhotoOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PetUploadStrategy implements UploadStrategy {
    private final FileService fileService = new FileService();
    private static final String PET_DIRECTORY = "/pet";

    @Override
    public String saveFile(FileUploadInput request) {
        return fileService.saveBase64File(request.getPdfBase64Pet(), PET_DIRECTORY);
    }

    @Override
    public PhotoOutput getFile(String filename) throws IOException {
        String base64 = fileService.getBase64File(filename, PET_DIRECTORY);
        String format = fileService.getMimeType(fileService.downloadFile(filename, PET_DIRECTORY));
        return new PhotoOutput(base64, format);
    }

    @Override
    public boolean deleteFile(String filename) {
        return fileService.deleteFile(filename, PET_DIRECTORY);
    }
}
