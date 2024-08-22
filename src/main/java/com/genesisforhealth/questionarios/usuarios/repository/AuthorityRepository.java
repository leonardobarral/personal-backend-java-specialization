package com.genesisforhealth.questionarios.usuarios.repository;

import com.genesisforhealth.questionarios.usuarios.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
