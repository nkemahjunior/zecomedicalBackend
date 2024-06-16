package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.general.projections.lab.LabBloodBankProjection;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.model.Consultation;
import com.zeco.zecomedical.model.LabBloodBank;
import com.zeco.zecomedical.model.RegisteredPatients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabBloodBankRepository extends JpaRepository<LabBloodBank,Long> {

    List<LabRequestProjections> findByLabResultsBloodBank(Consultation consultation);


    Page<LabRequestProjections> findByCompleted(Boolean completed, Pageable pageable);

    List<LabRequestProjections> findByCompletedAndPatientIDOrderByCreationTimestampDesc(Boolean completed, RegisteredPatients patient);



    Page<LabRequestProjections> findByCompletedAndPatientNameIgnoreCaseContaining(Boolean completed,String name,Pageable pageable);

    List<LabBloodBankProjection> findByLabResultsBloodBankAndCompleted(Consultation consultation,Boolean completed);
}
