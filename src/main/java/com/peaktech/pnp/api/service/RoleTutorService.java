package com.peaktech.pnp.api.service;

import com.peaktech.pnp.model.entity.RoleTutor;
import com.peaktech.pnp.model.input.RoleTutorInput;
import com.peaktech.pnp.repository.RoleTutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleTutorService {
    @Autowired
    private final RoleTutorRepository roleTutorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public RoleTutor findById(Long id) {
        return roleTutorRepository.findById(id).orElseThrow(() -> new RuntimeException("Role não encontrada"));
    }

    public List<RoleTutor> listAllRole() {
        return roleTutorRepository.findAll();
    }

    public RoleTutor save(RoleTutorInput roleTutorInput) {
        RoleTutor roleTutor = modelMapper.map(roleTutorInput, RoleTutor.class);
        roleTutor.setRoleTutor(roleTutorInput.getRoleTutor().toUpperCase());
        return roleTutorRepository.save(roleTutor);
    }

    public List<RoleTutor> listAllRoleTutor() {
        return roleTutorRepository.findAll();
    }

    public RoleTutor updateById(Long id, RoleTutorInput roleTutorInput) {
        RoleTutor roleTutor = findById(id);
        modelMapper.map(roleTutorInput, roleTutor);
        roleTutor.setRoleTutor(roleTutorInput.getRoleTutor().toUpperCase());
        return roleTutorRepository.save(roleTutor);
    }

    public void desactivateById(Long id) {
        RoleTutor roleTutor = findById(id);
        roleTutorRepository.delete(roleTutor);
    }
    public RoleTutor findById(Long id) {
        return roleTutorRepository.findById(id).orElseThrow(() -> new RuntimeException("RoleTutor não encontrado"));
    }
    public boolean authenticateByEmail(String email) {
        return roleTutorRepository.existsByEmail(email);
    }
}
