package com.peaktech.pnp.api.service;

import com.peaktech.pnp.exception.InvalidPasswordException;
import com.peaktech.pnp.model.entity.RoleTutor;
import com.peaktech.pnp.model.entity.Tutor;
import com.peaktech.pnp.model.input.TutorInput;
import com.peaktech.pnp.model.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleTutorService roleTutorService;

    @PostConstruct
    private String encryptPassword(TutorInput tutorInput, Tutor tutor) {
        if (tutorInput.getPassword() != null && !tutorInput.getPassword().isEmpty() && !tutorInput.getPassword().equals(tutor.getPassword())) {
            return passwordEncoder.encode(tutorInput.getPassword());
        }
        return tutor.getPassword();
    }

    public Tutor deactivateById(Long id) {
        Tutor tutor = findById(id);
        tutor.setActived(false);
        return tutorRepository.save(tutor);
    }

    public List<Tutor> listAllUserDeactivate() {
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

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Tutor tutor = tutorRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        if (!tutor.getActived()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        RoleTutor role = roleTutorService.findById(tutor.getRoleTutor().getId());
        return User.builder()
                .username(tutor.getEmail())
                .password(tutor.getPassword())
                .roles(role.getRoleTutor())
                .build();
    }

    public Tutor authenticate(Tutor tutor) {
        UserDetails tutorDetails = loadUserByUsername(tutor.getEmail());
        if (passwordEncoder.matches(tutor.getPassword(), tutorDetails.getPassword())) {
            return findByEmail(tutor.getEmail()).get();
        }
        throw new InvalidPasswordException();
    }

    public boolean activedAccountTutor(String email) {
        Tutor tutor = tutorRepository.findByExistEmail(email).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
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
        return tutorRepository.findByExistEmail(email);
    }

    public void delete(Long id) {
        tutorRepository.deleteById(id);
    }

    private Tutor findById(Long id) {
        return tutorRepository.findById(id).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }
}
