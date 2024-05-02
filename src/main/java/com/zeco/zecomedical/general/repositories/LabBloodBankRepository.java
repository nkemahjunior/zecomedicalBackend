package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.Consultation;
import com.zeco.zecomedical.model.LabBloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabBloodBankRepository extends JpaRepository<LabBloodBank,Long> {

    List<LabBloodBank> findByLabResultsBloodBank(Consultation consultation);
}
