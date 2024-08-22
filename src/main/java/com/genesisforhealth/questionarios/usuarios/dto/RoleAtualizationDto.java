package com.genesisforhealth.questionarios.usuarios.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RoleAtualizationDto(
        @NotBlank(message = "O id da role é obrigatório!")
        String id,

        String description,

        String observation,

        List<String> authorities
) {
}
