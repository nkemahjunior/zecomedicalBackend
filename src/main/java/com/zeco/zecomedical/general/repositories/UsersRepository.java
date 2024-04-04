package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;



public interface UsersRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);

}
