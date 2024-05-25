package com.zeco.zecomedical.general.repositories;


import com.zeco.zecomedical.model.RegisteredPatients;
import com.zeco.zecomedical.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<RegisteredPatients,Long> {

   Optional<RegisteredPatients> findByPatientID(Users user);

   Boolean existsByPatientID(Users user);



}
