package com.zeco.zecomedical.general.repositories;


import com.zeco.zecomedical.model.WaitingLabResults;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WaitingLabResultsRepository extends JpaRepository<WaitingLabResults,Long> {

   Optional<List<WaitingLabResults >> findByDoctorIDAndAndCompleted(Long doctorID, Boolean completed);

   Optional<WaitingLabResults> findByConsultationID(UUID consultationID);
}
