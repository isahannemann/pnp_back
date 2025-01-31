package com.peaktech.pnp.api.controller;

import com.peaktech.pnp.api.service.upload.UserUploadService;
import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.photo.PhotoOutput;
import com.peaktech.pnp.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UserUploadController {

    private final UserUploadService userUploadService;

    @Autowired
    public UserUploadController(UserUploadService userUploadService) {
        this.userUploadService = userUploadService;
    }

    @PostMapping("/save-photo")
    public ResponseEntity<String> saveUserPhoto(@RequestBody FileUploadInput request,
                                                @RequestParam UserType userType) {
        try {
            String result = userUploadService.saveUserPhoto(request, userType);
            return ResponseEntity.ok("Arquivo salvo com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar o arquivo");
        }
    }

    @GetMapping("/get-photo/{filename}")
    public ResponseEntity<PhotoOutput> getUserPhoto(@PathVariable String filename,
                                                   @RequestParam UserType userType) {
        try {
            PhotoOutput fotoOutput = userUploadService.getUserPhoto(filename, userType);
            return ResponseEntity.ok(fotoOutput);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete-photo/{filename}")
    public ResponseEntity<String> deleteUserPhoto(@PathVariable String filename,
                                                  @RequestParam UserType userType) {
        try {
            boolean deleted = userUploadService.deleteUserPhoto(filename, userType);
            if (deleted) {
                return ResponseEntity.ok("Arquivo excluído com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Erro ao excluir o arquivo");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir o arquivo");
        }
    }
}
