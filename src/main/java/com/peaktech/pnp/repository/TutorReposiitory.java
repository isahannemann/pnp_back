package com.peaktech.pnp.repository;

import com.peaktech.pnp.model.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {


    static List<Tutor> findByActivedTutorTrue();

    Optional<Tutor> findByIdAndActivedTutorTrue(Long id);

    @Query("select u from Tutor u where u.activedTutor = false")
    List<Tutor> findAllDesactivedTutor();

    @Query("select u from Tutor u where u.id = :id and u.activedTutor = false")
    Optional<Tutor> findByIdDesactivedTutor(Long id);

    @Query("SELECT fotoTutor FROM Pet WHERE id = :idTutor")
    String findByFotoTutor(Long idTutor);
}