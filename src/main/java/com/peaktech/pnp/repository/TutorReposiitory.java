package com.peaktech.pnp.repository;

import com.peaktech.pnp.model.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    List<Tutor> findByActivedTutor();

    Optional<Tutor> findByIdActivedTutor(Long id);

    List<Tutor> findByDeactivatedTutor();

    Optional<Tutor> findByIdDeactivatedTutor(Long id);

    @Query("SELECT t.fotoTutor FROM Tutor t WHERE t.id = :idTutor")
    String findPhotoTutorById(Long idTutor);
}
