package com.peaktech.pnp.api.service;

import com.peaktech.pnp.core.utils.Utils;
import com.peaktech.pnp.model.entity.Pet;
import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.input.PetInput;
import com.peaktech.pnp.model.output.FotoPetOutput;
import com.peaktech.pnp.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    @Autowired
    private final PetRepository petRepository;
    @Autowired
    private final UploadService uploadService;
    @Autowired
    private final Utils utils;

    @Value("${filediretorio}")
    private String filedir;
    private String caminhoFotosPet;

    @PostConstruct
    public void init() {
        filedir = filedir.replaceAll("\"", "").replaceAll(";", "");
        caminhoFotosPet = filedir + "fotos/";
        System.out.println("Caminho das fotos do pet inicializado: " + caminhoFotosPet);
    }

    public List<Pet> listAll() {

            List<Pet> pets = PetRepository.findByActivedPetTrue();

            for (Pet pet : pets) {
                if (pet.getFotoPet() != null) {
                    String fullPath = caminhoFotosPet + pet.getFotoPet();
                    System.out.println("Caminho da foto do pet " + pet.getName() + ": " + fullPath);

                    try {
                        FotoPetOutput fotoPetOutput = UploadService.buscarArquivoBase64ComFormato(pet.getFotoPet(), caminhoFotosPet);
                        pet.setFotoPet(fotoPetOutput.getFotoBase64Pet());
                        System.out.println(fotoPetOutput.getFotoBase64Pet());
                        pet.setFormatoFotoPet(fotoPetOutput.getFormatoFotoPet();
                    } catch (Exception e) {
                        System.err.println("Erro ao carregar a foto para o pet " + pet.getName() + ": " + e.getMessage());
                        pet.setFotoPet(null);
                        pet.setFormatoFotoPet(null);
                    }
                } else {
                    System.out.println("Pet " + pet.getName() + " não possui foto.");
                }
            }
            return pets;
        }
    }
    public Pet findById(Long id) {
        Pet pet = PetRepository.findByIdAndActivedPetTrue(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (pet.getFotoPet() != null) {
            try {
                FotoPetOutput fotoPetOutput = UploadService.buscarArquivoBase64ComFormato(pet.getFotoPet(), caminhoFotosPet);
                pet.setFotoPet(fotoPetOutput.getFotoBase64Pet());
                pet.setFormatoFotoPet(fotoPetOutput.getFormatoFotoPet();
            } catch (Exception e) {
                System.err.println("Erro ao carregar a foto para o pet " + pet.getName() + ": " + e.getMessage());
                pet.setFotoPet(null);
                pet.setFormatoFotoPet(null);
            }
        }

        return pet;
    }
    public Pet updateByIdPet(Long id, PetInput userInput) {
        Pet pet = findById(id);
        String uniqueFileName = salvarFotoPerfilPet(userInput, pet.getIdPet(), "");

        pet.setName(userInput.getName());
        pet.setNascimento(((userInput.getNascimento());
        pet.setTutor(userInput.getTutor());
        pet.setBanho(userInput.getBanho());
        pet.setAlimentacao(userInput.getAlimentacao());
        pet.setVacina(userInput.getVacina());
        pet.setVermifugo(userInput.getVermifugo());
        pet.setMedicamento(userInput.getMedicamento());
        pet.setObs(userInput.getObs());
        pet.setFotoPet(uniqueFileName);

        return PetRepository.save(pet);
    }
    private String salvarFotoPerfilPet(PetInput petInput, Long idPet, String uniqueFileName) {
        if (petInput.getFotoPet() != null && utils.isBase64(petInput.getFotoPet())) {
            String nomeFoto =PetRepository.findByFotoPet(idPet);

            if (nomeFoto != null) {
                uploadService.deleteFile(nomeFoto, caminhoFotosPet);
            }

            FileUploadInput fileUploadInput = new FileUploadInput();
            fileUploadInput.setPdfBase64(petInput.getFotoPet());
            fileUploadInput.setFileName("foto");
            uniqueFileNamePet = this.uploadService.saveBase64(fileUploadInput, caminhoFotosPet);
        }
        return uniqueFileNamePet;
    }

}
