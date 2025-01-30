package com.peaktech.pnp.repository;

import com.peaktech.pnp.model.entity.RoleTutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleTutorRepository extends JpaRepository<RoleTutor, Long> {

}

