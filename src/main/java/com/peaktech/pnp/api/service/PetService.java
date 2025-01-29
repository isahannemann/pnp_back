package com.peaktech.pnp.api.service;

import com.peaktech.pnp.model.entity.Pet;
import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.input.PetInput;
import com.peaktech.pnp.model.output.FotoOutput;
import com.peaktech.pnp.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService implements UserDetailsService {

    @Autowired
    private final PetRepository petRepository;
    @Autowired
    private final UploadService uploadService;

    @Value("${filediretorio}")
    private String filedir;
    private String caminhoFotos;

    @PostConstruct
    public void init() {
        filedir = filedir.replaceAll("\"", "").replaceAll(";", "");
        caminhoFotos = filedir + "fotos/";
        System.out.println("Caminho das fotos inicializado: " + caminhoFotos);
    }

    public List<Pet> listAll() {

        public List<Pet> listAll() {

            List<Pet> users = PetRepository.findByActivedTrue();

            for (Pet user : pets) {
                if (user.getFoto() != null) {
                    String fullPath = caminhoFotos + user.getFoto();
                    System.out.println("Caminho da foto do usuário " + pet.getName() + ": " + fullPath);

                    try {
                        FotoOutput fotoOutput = UploadService.buscarArquivoBase64ComFormato(user.getFoto(), caminhoFotos);
                        user.setFoto(fotoOutput.getFoto_base64());
                        System.out.println(fotoOutput.getFoto_base64());
                        user.setFormato_foto(fotoOutput.getFormato_foto());
                    } catch (Exception e) {
                        System.err.println("Erro ao carregar a foto para o usuário " + user.getName() + ": " + e.getMessage());
                        user.setFoto(null);
                        user.setFormato_foto(null);
                    }
                } else {
                    System.out.println("Usuário " + pet.getName() + " não possui foto.");
                }
            }
            return users;
        }
    }
    public Pet findById(Long id) {
        Pet pet = petRepository.findByIdAndActivedTrue(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (pet.getFoto() != null) {
            try {
                FotoOutput fotoOutput = uploadService.buscarArquivoBase64ComFormato(user.getFoto(), caminhoFotos);
                pet.setFoto(fotoOutput.getFoto_base64());
                pet.setFormato_foto(fotoOutput.getFormato_foto());
            } catch (Exception e) {
                System.err.println("Erro ao carregar a foto para o usuário " + pet.getName() + ": " + e.getMessage());
                pet.setFoto(null);
                pet.setFormato_foto(null);
            }
        }

        return pet;
    }
    public Pet updateById(Long id, PetInput userInput) {
        Pet pet = findById(id);
        String uniqueFileName = salvarFotoPerfilPet(userInput, pet.getId(), "");

        pet.setName(userInput.getName());
        pet.setNascimento(((userInput.getNascimento());
        pet.setTutor(userInput.getTutor());
        pet.setBanho(userInput.getBanho());
        pet.setAlimentacao(userInput.getAlimentacao());
        pet.setVacina(userInput.getVacina());
        pet.setVermifugo(userInput.getVermifugo());
        pet.setMedicamento(userInput.getMedicamento());
        pet.setObs(userInput.getObs());
        pet.setFoto(uniqueFileName);

        return petRepository.save(pet);
    }
    private String salvarFotoPerfilPet(PetInput petInput, Long idPet, String uniqueFileName) {
        if (petInput.getFoto() != null && utils.isBase64(petInput.getFoto())) {
            String nomeFoto = userRepository.findByFoto(idPet);

            if (nomeFoto != null) {
                uploadService.deleteFile(nomeFoto, caminhoFotos);
            }

            FileUploadInput fileUploadInput = new FileUploadInput();
            fileUploadInput.setPdfBase64(petInput.getFoto());
            fileUploadInput.setFileName("foto");
            uniqueFileName = this.uploadService.saveBase64(fileUploadInput, caminhoFotos);
        }
        return uniqueFileName;
    }
}
