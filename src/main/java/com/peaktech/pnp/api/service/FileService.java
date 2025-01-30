package com.peaktech.pnp.api.service;

import org.springframework.util.Base64Utils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileService {

    public String saveBase64File(String base64Data, String path) {
        try {
            String uniqueFileName = UUID.randomUUID().toString();
            byte[] data = Base64Utils.decodeFromString(base64Data);
            Path filePath = Paths.get(path, uniqueFileName + ".png");
            Files.createDirectories(filePath.getParent());
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                fos.write(data);
            }
            return uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar arquivo.");
        }
    }

    public File downloadFile(String filename, String path) throws IOException {
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String regex = Pattern.quote(filename);
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(file.getName());

                    if (matcher.find()) {
                        return file;
                    }
                }
            }
        } else {
            throw new RuntimeException("Diretório não encontrado");
        }
        return null;
    }

    public boolean deleteFile(String filename, String path) {
        File file = new File(path, filename + ".png");
        return file.exists() && file.delete();
    }

    public String getBase64File(String filename, String path) throws IOException {
        File file = downloadFile(filename, path);
        if (file == null) {
            throw new RuntimeException("Arquivo não encontrado");
        }

        byte[] fileBytes = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    public String getMimeType(File file) throws IOException {
        Path path = file.toPath();
        String mimeType = Files.probeContentType(path);
        return (mimeType != null) ? mimeType : "application/octet-stream";
    }
}

