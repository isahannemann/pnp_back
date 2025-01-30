package com.peaktech.pnp.model.output;

import com.peaktech.pnp.api.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class PetPhotoOutputStrategy implements PhotoOutputStrategy {

    private final FileService fileService = new FileService();
    private static final String PET_DIRECTORY = "/caminho/para/pet";

    @Override
    public PhotoOutput getPhoto(String filename) throws IOException {
        String base64 = fileService.getBase64File(filename, PET_DIRECTORY);
        String format = fileService.getMimeType(fileService.downloadFile(filename, PET_DIRECTORY));
        return new PhotoOutput(base64, format);
    }
}
