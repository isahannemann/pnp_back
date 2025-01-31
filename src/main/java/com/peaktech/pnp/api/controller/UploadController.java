package com.peaktech.pnp.api.controller;

import com.peaktech.pnp.api.service.upload.UploadService;
import com.peaktech.pnp.model.input.FileUploadInput;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.peaktech.pnp.core.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private final UploadService uploadService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestBody FileUploadInput request, String caminho) {
        Utils utils = new Utils();
        try {
            if (utils.isBase64(request.getPdfBase64())) {
                uploadService.saveBase64(request, "");
                return ResponseEntity.status(200).body("Arquivo salvo com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Arquivo não é base64");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar o arquivo");
        }
    }

    @PostMapping("/download-file/{nomeArquivo}/{caminho}")
    public void downloadFile(@PathVariable String nomeArquivo, @PathVariable String caminho, HttpServletResponse response) throws IOException {


        File arquivo = uploadService.downloadFile(nomeArquivo, caminho);

        if (arquivo.isFile()) {
            String mimeType = uploadService.getMimeType(arquivo);
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\"");

            try (InputStream inputStream = Files.newInputStream(arquivo.toPath()); OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setContentType("text/plain");
            response.getWriter().write("Arquivo não encontrado");
        }
    }
}
