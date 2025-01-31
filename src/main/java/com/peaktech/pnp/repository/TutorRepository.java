package com.peaktech.pnp.repository;

import com.peaktech.pnp.model.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    // Lista todos os tutores ativos
    List<Tutor> findByActivedTutor();

    // Busca tutor ativo por usernametutor
    Optional<Tutor> findByUserNameTutorAndActived(String tutorUsername);

    // Lista todos os tutores inativos
    List<Tutor> findByDeactivateTutor();

    // Busca tutor inativo por usernametutor
    Optional<Tutor> findByUserNameTutorAndDeactivate(String tutorUsername);

    // Busca tutor pelo usernametutor
    Optional<Tutor> findByUserNameTutor(String tutorUsername);

    // Deleta tutor pelo usernametutor
    void deleteByUserNameTutor(String tutorUsername);

    // Busca a foto do tutor pelo id
    @Query("SELECT t.fotoTutor FROM Tutor t WHERE t.username = :tutorUsername")
    String findPhotoTutorByUserNameTutor(String tutorUsername);
}
