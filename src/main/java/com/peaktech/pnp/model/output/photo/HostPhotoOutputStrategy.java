﻿package com.peaktech.pnp.model.output.photo;

import com.peaktech.pnp.api.service.FileService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HostPhotoOutputStrategy implements PhotoOutputStrategy {

    private final FileService fileService = new FileService();
    private static final String HOST_DIRECTORY = "/caminho/para/host";

    @Override
    public PhotoOutput getPhoto(String filename) throws IOException {
        String base64 = fileService.getBase64File(filename, HOST_DIRECTORY);
        String format = fileService.getMimeType(fileService.downloadFile(filename, HOST_DIRECTORY));
        return new PhotoOutput(base64, format);
    }
}
