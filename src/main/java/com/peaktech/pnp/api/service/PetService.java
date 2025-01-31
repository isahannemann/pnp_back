package com.peaktech.pnp.api.service;

import com.peaktech.pnp.api.service.upload.UploadStrategy;
import com.peaktech.pnp.model.entity.Pet;
import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.input.PetInput;
import com.peaktech.pnp.model.output.photo.PhotoOutput;
import com.peaktech.pnp.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final UploadStrategy uploadStrategy;

    @Value("${pet.directory}")
    private String pathPhotosPet;

    @PostConstruct
    public void init() {
        System.out.println("Caminho das fotos do pet inicializado: " + pathPhotosPet);
    }

    public List<Pet> listAll() {
        List<Pet> pets = PetRepository.findByActivedPetTrue();

        for (Pet pet : pets) {
            if (pet.getPhotoPet() != null) {
                try {
                    PhotoOutput PetPhotoOutputStrategy = uploadStrategy.getFile(pet.getPhotoPet());
                    pet.setPhotoPet(PetPhotoOutputStrategy.getBase64());
                    pet.setFormatPhotoPet(PetPhotoOutputStrategy.getFormat());
                } catch (Exception e) {
                    System.err.println("Erro ao carregar a foto para o pet " + pet.getName() + ": " + e.getMessage());
                    pet.setPhotoPet(null);
                    pet.setFormatPhotoPet(null);
                }
            }
        }
        return pets;
    }

    public Optional<Pet> findById(Long id) {
        Pet pet = petRepository.findByIdAndActivedPetTrue(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        if (pet.getPhotoPet() != null) {
            try {
                PhotoOutput PhotoPetOutput = uploadStrategy.getFile(pet.getPhotoPet());
                pet.setPhotoPet(PhotoPetOutput.getBase64());
                pet.setFormatPhotoPet(PhotoPetOutput.getFormat());
            } catch (Exception e) {
                System.err.println("Erro ao carregar a foto para o pet " + pet.getName() + ": " + e.getMessage());
                pet.setPhotoPet(null);
                pet.setFormatPhotoPet(null);
            }
        }
        return petRepository.findById(id);
    }

    public Pet updateByIdPet(Long id, PetInput userInput) {
        Pet pet = findById(id);
        String uniqueFileName = savePetProfilePhoto(userInput, pet.getIdPet());

        pet.setName(userInput.getName());
        pet.setBirth(userInput.getBirth());
        pet.setTutor(userInput.getTutor());
        pet.setBath(userInput.getBath());
        pet.setFeed(userInput.getFeed());
        pet.setVaccinate(userInput.getVaccine());
        pet.setDeworm(userInput.getDeworm());
        pet.setDeworm(userInput.getDeworm());
        pet.setMedicine(userInput.getMedicine());
        pet.setObservation(userInput.getObservation());
        pet.setPhotoPet(uniqueFileName);

        return petRepository.save(pet);
    }

    private String savePetProfilePhoto(PetInput petInput, Long idPet) {
        if (petInput.getPhotoPet() != null) {
            try {
                String namePhoto = petRepository.findByPhotoPet(idPet);

                if (namePhoto != null) {
                    uploadStrategy.deleteFile(namePhoto);
                }

                return uploadStrategy.saveFile(new FileUploadInput(petInput.getPhotoPet(), "foto"));
            } catch (Exception e) {
                System.err.println("Erro ao salvar foto do pet: " + e.getMessage());
            }
        }
        return null;
    }
}
