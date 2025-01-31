package com.peaktech.pnp.repository;

import com.peaktech.pnp.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet , Long> {


    static List<Pet> findByActivedPetTrue();

    Optional<Pet> findByIdAndActivedPetTrue(Long id);

    @Query("select u from Pet u where u.activedPet = false")
    List<Pet> findAllPetDesactived();

    @Query("select u from Pet u where u.id = :id and u.activedPet = false")
    Optional<Pet> findByIdDesactivedPet(Long id);

    @Query("SELECT fotoPet FROM Pet WHERE id = :idPet")
    String findByPhotoPet(Long idPet);
}