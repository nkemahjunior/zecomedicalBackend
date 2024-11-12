package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<Roles,Integer> {
}
