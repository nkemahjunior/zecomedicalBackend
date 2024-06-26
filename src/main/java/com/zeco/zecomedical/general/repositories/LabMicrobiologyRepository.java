package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.general.projections.lab.LabMicrobiologyProjection;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.model.Consultation;
import com.zeco.zecomedical.model.LabBloodBank;
import com.zeco.zecomedical.model.LabMicrobiology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LabMicrobiologyRepository extends JpaRepository<LabMicrobiology,Long> {

    List<LabRequestProjections> findByLabResultsMicrobiology(Consultation consultation);

    Page<LabRequestProjections> findByCompleted(Boolean completed, Pageable pageable);

    Page<LabRequestProjections> findByCompletedAndPatientNameIgnoreCaseContaining(Boolean completed,String name,Pageable pageable);

    List<LabMicrobiologyProjection> findByLabResultsMicrobiologyAndCompleted(Consultation consultation, Boolean completed);


}
