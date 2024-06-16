package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.Users;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DoctorsRepository extends JpaRepository<Doctors,Long> {



    List<Doctors> findBySpecialityIgnoreCaseContaining(String speciality);
    Optional<Doctors> findByUuid(Users uuid);

    Boolean existsByUuid(Users uuid);
}
