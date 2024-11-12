package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.LabTechnicians;
import com.zeco.zecomedical.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabTechnicianRepository extends JpaRepository<LabTechnicians,Long> {

    Optional<LabTechnicians> findByUserID(Users user);

    Boolean existsByUserID(Users user);
}
