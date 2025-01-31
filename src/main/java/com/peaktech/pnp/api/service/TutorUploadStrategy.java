package com.peaktech.pnp.api.service;

import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.photo.PhotoOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TutorUploadStrategy implements UploadStrategy {
    private final FileService fileService = new FileService();
    private static final String TUTOR_DIRECTORY = "/tutor";

    @Override
    public String saveFile(FileUploadInput request) {
        return fileService.saveBase64File(request.getPdfBase64Tutor(), TUTOR_DIRECTORY);
    }

    @Override
    public PhotoOutput getFile(String filename) throws IOException {
        String base64 = fileService.getBase64File(filename, TUTOR_DIRECTORY);
        String format = fileService.getMimeType(fileService.downloadFile(filename, TUTOR_DIRECTORY));
        return new PhotoOutput(base64, format);
    }

    @Override
    public boolean deleteFile(String filename) {
        return fileService.deleteFile(filename, TUTOR_DIRECTORY);
    }
}

