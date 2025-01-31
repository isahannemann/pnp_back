package com.peaktech.pnp.model.output;

import com.peaktech.pnp.model.defaults.DefaultEntityDTO;
import com.peaktech.pnp.model.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetOutput extends DefaultEntityDTO {

    private String name;
    private LocalDate birth;
    private String tutor;
    private String bath;
    private String feed;
    private String vaccinate;
    private String deworm;
    private String medicine;
    private String observation;
    private String PhotoBase64Pet;
    private String formatPhotoPet;

    public PetOutput(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("O objeto Pet não pode ser nulo.");
        }
        this.setIdPet(pet.getIdPet());
        this.name = pet.getName();
        this.birth = pet.getBirth();
        this.tutor = pet.getTutor();
        this.bath = pet.getBath();
        this.feed = pet.getFeed();
        this.vaccinate = pet.getVaccinate();
        this.deworm = pet.getDeworm();
        this.medicine = pet.getMedicine();
        this.observation = pet.getObservation();
        this.PhotoBase64Pet = pet.getPhotoPet() != null ? pet.getPhotoPet() : "";
        this.formatPhotoPet = pet.getFormatPhotoPet() != null ? pet.getFormatPhotoPet() : "";
        this.setActivedPet(pet.getActivedPet());
    }
}
