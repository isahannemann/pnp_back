package com.peaktech.pnp.api.service;

import com.peaktech.pnp.api.service.upload.UploadService;
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
        List<Pet> pets = petRepository.findByActivedPet();

        for (Pet pet : pets) {
            if (pet.getPhotoPet() != null) {
                try {
                    PhotoOutput petPhotoOutputStrategy = uploadStrategy.getFile(pet.getPhotoPet());
                    pet.setPhotoPet(petPhotoOutputStrategy.getBase64());
                    pet.setFormatPhotoPet(petPhotoOutputStrategy.getFormat());
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

    public Optional<Pet> findByUsernamePet(String usernamePet) {
        Pet pet = petRepository.findByUsernamePetAndActivedPet(usernamePet)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        if (pet.getPhotoPet() != null) {
            try {
                PhotoOutput photoPetOutput = uploadStrategy.getFile(pet.getPhotoPet());
                pet.setPhotoPet(photoPetOutput.getBase64());
                pet.setFormatPhotoPet(photoPetOutput.getFormat());
            } catch (Exception e) {
                System.err.println("Erro ao carregar a foto para o pet " + pet.getName() + ": " + e.getMessage());
                pet.setPhotoPet(null);
                pet.setFormatPhotoPet(null);
            }
        }
        return pet;
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

        return Optional.of(pet);
    }

    public List<Pet> findByTutor(String tutorUsername) {
        List<Pet> pets = petRepository.findByTutorUsernameAndActivedPet(tutorUsername);

        for (Pet pet : pets) {
            if (pet.getPhotoPet() != null) {
                try {
                    PhotoOutput petPhotoOutputStrategy = uploadStrategy.getFile(pet.getPhotoPet());
                    pet.setPhotoPet(petPhotoOutputStrategy.getBase64());
                    pet.setFormatPhotoPet(petPhotoOutputStrategy.getFormat());
                } catch (Exception e) {
                    System.err.println("Erro ao carregar a foto para o pet " + pet.getName() + ": " + e.getMessage());
                    pet.setPhotoPet(null);
                    pet.setFormatPhotoPet(null);
                }
            }
        }
        return pets;
    }

    public Pet updateByUsernamePet(String usernamePet, PetInput petInput) {
        Pet pet = findByUsernamePet(usernamePet).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
        String uniqueFileName = savePetProfilePhoto(petInput, pet.getUsernamePet());

        pet.setName(petInput.getName());
        pet.setBirth(petInput.getBirth());
        pet.setTutor(petInput.getTutor());
        pet.setBath(petInput.getBath());
        pet.setFeed(petInput.getFeed());
        pet.setVaccinate(petInput.getVaccine());
        pet.setDeworm(petInput.getDeworm());
        pet.setMedicine(petInput.getMedicine());
        pet.setObservation(petInput.getObservation());
        pet.setPhotoPet(uniqueFileName);

        return petRepository.save(pet);
    }

    private String savePetProfilePhoto(PetInput petInput, Long idPet) {
        if (petInput.getPhotoPet() != null) {
            try {
                String namePhoto = petRepository.findByPhotoPet(idPet);

            if (nomeFoto != null) {
                uploadService.deleteFile(nomeFoto, caminhoFotos);

                private String savePetProfilePhoto (PetInput petInput, String petUsername){
                    if (petInput.getPhotoPet() != null) {
                        try {
                            String namePhoto = petRepository.findByPhotoPet(petUsername);


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

                public Pet save (PetInput petInput){
                    Pet pet = new Pet();
                    pet.setUsernamePet(petInput.getPetUsername());
                    pet.setName(petInput.getName());
                    pet.setBirth(petInput.getBirth());
                    pet.setTutor(petInput.getTutor());
                    pet.setBath(petInput.getBath());
                    pet.setFeed(petInput.getFeed());
                    pet.setVaccinate(petInput.getVaccine());
                    pet.setDeworm(petInput.getDeworm());
                    pet.setMedicine(petInput.getMedicine());
                    pet.setObservation(petInput.getObservation());

                    // Verifica se há foto e faz o upload, se necessário
                    String uniqueFileName = savePetProfilePhoto(petInput, pet.getUsernamePet());
                    pet.setPhotoPet(uniqueFileName);

                    return petRepository.save(pet);
                }
            }
