package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.DoctorsAvailableForAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DoctorsAvailableRepository extends JpaRepository<DoctorsAvailableForAppointment,Long> {

    List<DoctorsAvailableForAppointment> findByDoctorID(Doctors doctor);
    //List<DoctorsAvailableForAppointment> findAll(Pageable pageable);

    Page<DoctorsAvailableForAppointment> findByTimeFromGreaterThan(LocalDateTime time, Pageable pageable);
}
