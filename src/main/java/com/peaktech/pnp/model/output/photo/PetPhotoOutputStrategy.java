package com.peaktech.pnp.model.output.photo;

import com.peaktech.pnp.api.service.FileService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Component
public class PetPhotoOutputStrategy implements PhotoOutputStrategy {

    private final FileService fileService;

    @Value("${pet.directory}")
    private String petDirectory;

    public PetPhotoOutputStrategy(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public PhotoOutput getPhoto(String filename) throws IOException {
        String base64 = fileService.getBase64File(filename, petDirectory);
        String format = fileService.getMimeType(fileService.downloadFile(filename, petDirectory));
        return new PhotoOutput(base64, format);
    }
}
