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

    Optional<Pet> findByIdAndActivedPet(Long id);

    @Query("SELECT u FROM Pet u WHERE u.activedPet = false")
    List<Pet> findAllPetDeactivate();

    @Query("SELECT u FROM Pet u WHERE u.id = :id AND u.activedPet = false")
    Optional<Pet> findByIdDeactivatePet(Long id);

    @Query("SELECT fotoPet FROM Pet WHERE id = :idPet")
    String findByPhotoPet(Long idPet);

    @Query("SELECT p FROM Pet p WHERE p.tutorUsername = :tutorUsername AND p.activedPet = true")
    List<Pet> findByTutorUsernameAndActivedPet(@Param("tutorUsername") String tutorUsername);

    @Query("SELECT p FROM Pet p WHERE p.usernamePet = :usernamePet AND p.activedPet = true")
    Optional<Pet> findByUsernamePetAndActivedPet(@Param("usernamePet") String usernamePet);
}
