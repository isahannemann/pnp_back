package com.peaktech.pnp.model.output;

import com.peaktech.pnp.model.output.PhotoOutput;
import org.springframework.stereotype.Service;

@Service
public class TutorPhotoOutputStrategy implements PhotoOutputStrategy {

    private final FileService fileService = new FileService();
    private static final String TUTOR_DIRECTORY = "/caminho/para/tutor";

    @Override
    public PhotoOutput getPhoto(String filename) throws IOException {
        String base64 = fileService.getBase64File(filename, TUTOR_DIRECTORY);
        String format = fileService.getMimeType(fileService.downloadFile(filename, TUTOR_DIRECTORY));
        return new PhotoOutput(base64, format);
    }
}
