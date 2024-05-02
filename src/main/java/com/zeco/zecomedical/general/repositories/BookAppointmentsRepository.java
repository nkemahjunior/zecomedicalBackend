package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.DoctorsAvailableForAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAppointmentsRepository extends JpaRepository<DoctorsAvailableForAppointment,Long> {
}
