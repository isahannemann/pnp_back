package com.peaktech.pnp.model.output;

import com.peaktech.pnp.model.entity.RoleTutor;
import com.peaktech.pnp.model.entity.Tutor;

import java.util.List;
import java.util.stream.Collectors;

    public class RoleTutorOutput {

        private Long id;
        private String email;
        private Long tutorId;
        private List<Long> petIds;

        // Construtor
        public RoleTutorOutput(RoleTutor roleTutor) {
            this.id = roleTutor.getId();
            this.email = roleTutor.getTutor().getEmail();
            this.tutorId = roleTutor.getTutor().getId();
            this.petIds = getPetIds(roleTutor.getTutor());
        }

        private List<Long> getPetIds(Tutor tutor) {
            return tutor.getPets() != null ?
                    tutor.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()) :
                    null;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Long getTutorId() {
            return tutorId;
        }

        public void setTutorId(Long tutorId) {
            this.tutorId = tutorId;
        }

        public List<Long> getPetIds() {
            return petIds;
        }

        public void setPetIds(List<Long> petIds) {
            this.petIds = petIds;
        }
    }

