package com.peaktech.pnp.repository;

import com.peaktech.pnp.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<User, Long> {

    static List<Pet> findByActivedTrue();

    Optional<Pet> findByIdAndActivedTrue(Long id);

    @Query("select u from User u where u.actived = false")
    List<Pet> findAllUserDesactived();

    @Query("select u from User u where u.id = :id and u.actived = false")
    Optional<Pet> findByIdDesactived(Long id);
    
    @Query("SELECT foto FROM Pet WHERE id = :idPet")
    String findByFoto(Long idPet);
}