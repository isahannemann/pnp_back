package com.peaktech.pnp.api.service;

import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.PhotoOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HostUploadStrategy implements UploadStrategy {
    private final FileService fileService = new FileService();
    private static final String HOST_DIRECTORY = "/host";

    @Override
    public String saveFile(FileUploadInput request) {
        return fileService.saveBase64File(request.getPdfBase64Host(), HOST_DIRECTORY);
    }

    @Override
    public PhotoOutput getFile(String filename) throws IOException {
        String base64 = fileService.getBase64File(filename, HOST_DIRECTORY);
        String format = fileService.getMimeType(fileService.downloadFile(filename, HOST_DIRECTORY));
        return new PhotoOutput(base64, format);
    }

    @Override
    public boolean deleteFile(String filename) {
        return fileService.deleteFile(filename, HOST_DIRECTORY);
    }
}
