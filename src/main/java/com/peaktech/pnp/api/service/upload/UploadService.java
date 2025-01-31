package com.peaktech.pnp.api.service.upload;

import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.FotoOutput;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadService {

    public String saveBase64(FileUploadInput request, String path) {
        try {
            String uniqueFileName = UUID.randomUUID().toString();
            byte[] data = Base64Utils.decodeFromString(request.getPdfBase64());
            Path filePath = Paths.get(path, uniqueFileName + ".png");
            Files.createDirectories(filePath.getParent());
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                fos.write(data);
            }
            return uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar arquivo");
        }
    }
    public static File downloadFile(String filename, String path) throws IOException {

        File diretorio = new File(path);
        File[] arquivos = diretorio.listFiles();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    String nomeArquivo = arquivo.getName();
                    String regex = Pattern.quote(filename);
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(nomeArquivo);

                    if (matcher.find()) {
                        return arquivo;
                    }
                }
            }
        } else {
            throw new RuntimeException("Diretório não encontrado");
        }

        return null;
    }

    public ResponseEntity<?> getFile(String filename, String path, HttpServletResponse response) throws IOException {
        File arquivo = downloadFile(filename, path);

        if (arquivo != null) {
            String mimeType = getMimeType(arquivo);
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

            try (InputStream inputStream = Files.newInputStream(arquivo.toPath()); OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado");
        }
    }

    public static String getMimeType(File file) throws IOException {
        Path path = file.toPath();
        String mimeType = Files.probeContentType(path);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        return mimeType;
    }

    public static FotoOutput buscarArquivoBase64ComFormato(String foto, String path) throws IOException {
        File arquivo = downloadFile(foto, path);
        String formato = getMimeType(arquivo);

        try {
            byte[] fileBytes = Files.readAllBytes(arquivo.toPath());
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);
            return new FotoOutput(base64Encoded, formato);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao converter arquivo para base64");
        }
    }

    public boolean deleteFile(String nomeFoto, String caminhoFotos) {
        nomeFoto = nomeFoto + ".png";

        File arquivo = new File(caminhoFotos, nomeFoto);

        if (arquivo.exists()) {
            return arquivo.delete();
        } else {
            return false;
        }
    }
}
