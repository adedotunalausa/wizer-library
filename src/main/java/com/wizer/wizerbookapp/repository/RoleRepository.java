package com.wizer.wizerbookapp.repository;

import com.wizer.wizerbookapp.enums.RoleType;
import com.wizer.wizerbookapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
