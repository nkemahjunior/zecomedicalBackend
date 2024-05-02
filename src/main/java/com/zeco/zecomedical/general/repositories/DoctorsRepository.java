package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.Users;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorsRepository extends JpaRepository<Doctors,Long> {



    List<Doctors> findBySpeciality(String speciality);
    Optional<Doctors> findByUuid(Users uuid);
}
