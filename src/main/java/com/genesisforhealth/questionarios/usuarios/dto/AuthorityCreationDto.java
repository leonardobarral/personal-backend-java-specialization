package com.genesisforhealth.questionarios.usuarios.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorityCreationDto(
        @NotBlank(message = "A descrição da permissão é obrigatória!")
        String authority,

        @NotBlank(message = "A observação é obrigatória!")
        String observation
) {
}
