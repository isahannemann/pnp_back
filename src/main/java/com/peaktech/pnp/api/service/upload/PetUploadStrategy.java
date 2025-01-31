package com.peaktech.pnp.api.service.upload;

import com.peaktech.pnp.api.service.FileService;
import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.photo.PhotoOutput;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PetUploadStrategy implements UploadStrategy {

    private final FileService fileService;

    @Value("${pet.directory}")
    private String petDirectory;

    public PetUploadStrategy(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public String saveFile(FileUploadInput request) {
        return fileService.saveBase64File(request.getPdfBase64(), petDirectory);
    }

    @Override
    public PhotoOutput getFile(String filename) throws IOException {
        String base64 = fileService.getBase64File(filename, petDirectory);
        String format = fileService.getMimeType(fileService.downloadFile(filename, petDirectory));
        return new PhotoOutput(base64, format);
    }

    @Override
    public boolean deleteFile(String filename) {
        return fileService.deleteFile(filename, petDirectory);
    }
}
