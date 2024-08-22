package com.genesisforhealth.questionarios.usuarios.repository;

import com.genesisforhealth.questionarios.usuarios.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
