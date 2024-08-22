package com.genesisforhealth.questionarios.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RoleCreationDto(
        @NotBlank(message = "A descrição da role obrigatória!")
        String description,

        @NotBlank(message = "A observação é obrigatória!")
        String observation,

        @NotNull(message = "Uma lista de IDs de permissões é obrigatória!")
        List<String> authorities
) {
}
