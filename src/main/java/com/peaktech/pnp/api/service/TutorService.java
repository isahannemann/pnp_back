package com.peaktech.pnp.api.service;

import com.peaktech.pnp.model.entity.RoleTutor;
import com.peaktech.pnp.model.entity.Tutor;
import com.peaktech.pnp.model.input.TutorInput;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

public class TutorService {

    @PostConstruct
    private String encryptPassword(TutorInput tutorInput, Tutor tutor) {
        String password = tutor.getPassword();
        if (tutorInput.getPassword() != null && !tutorInput.getPassword().isEmpty() && !tutorInput.getPassword().equals(tutor.getPassword())) {
            password = passwordEncoder.encode(tutorInput.getPassword());
        }
        return password;
    }
    public Tutor deactivateById(Long id) {
        Tutor tutor = findById(id);
        tutor.setActived(false);
        return tutorRepository.save(tutor);
    }
    public List<Tutor> listAllUserDesactived() {
        return tutorRepository.findAllUserDesactived();
    }

    public Tutor findByIdDesactived(Long id) {
        return tutorRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }

    public Optional<Tutor> findByEmail(String email) {
        return tutorRepository.findByExistEmail(email);
    }

    public Tutor activedById(Long id) {
        Tutor tutor = findByIdDesactived(id);
        tutor.setActived(true);
        return tutorRepository.save(tutor);
    }

    @Override
    public UserDetails loadUserByUserName(String email) throws UsernameNotFoundException {
        Tutor tutor = tutorRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        if (!tutor.getActived()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else {
            RoleTutor role = roleTutorService.findById(tutor.getRoleTutor().getId());
            return org.springframework.security.core.userdetails.User.builder()
                    .username(tutor.getEmail())
                    .password(tutor.getPassword())
                    .roles(role.getRoleTutor())
                    .build();
        }
    }

    public Tutor authenticate(Tutor tutor) {
        TutorDetails tutorDetails = loadTutorrByUsername(tutor.getEmail());
        if (passwordEncoder.matches(tutor.getPassword(), tutorDetails.getPassword())) {
            return findByEmail(tutor.getEmail()).get();
        }
        throw new SenhaInvalidaException();
    }

    public boolean activedAccountTutor(String email) {
        Tutor tutor = this.tutorRepository.findByExistEmail(email).get();
        if (tutor.getActived()) {
            return false;
        }
        tutor.setActived(true);
        tutorRepository.save(tutor);
        return true;
    }

    public Tutor changePassword(Tutor updated, String password) {
        updated.setPassword(passwordEncoder.encode(password));
        return tutorRepository.save(updated);
    }

    public Optional<Tutor> findByExistEmail(String email) {
        return this.tutorRepository.findByExistEmail(email);
    }

    public void delete(Long id) {
        this.tutorRepository.deleteById(id);
    }
}
