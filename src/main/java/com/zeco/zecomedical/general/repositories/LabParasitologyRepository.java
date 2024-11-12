package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.general.projections.lab.LabParasitologyProjection;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.model.Consultation;
import com.zeco.zecomedical.model.LabImmunology;
import com.zeco.zecomedical.model.LabParasitology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabParasitologyRepository extends JpaRepository<LabParasitology,Long> {

    List<LabRequestProjections> findByLabResultsParasitology(Consultation consultation);

    Page<LabRequestProjections> findByCompleted(Boolean completed, Pageable pageable);

    Page<LabRequestProjections> findByCompletedAndPatientNameIgnoreCaseContaining(Boolean completed,String name,Pageable pageable);

    List<LabParasitologyProjection> findByLabResultsParasitologyAndCompleted(Consultation consultation,Boolean completed);
}
