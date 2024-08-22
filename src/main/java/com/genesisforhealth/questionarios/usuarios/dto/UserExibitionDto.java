package com.genesisforhealth.questionarios.usuarios.dto;

import com.genesisforhealth.questionarios.usuarios.model.User;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public record UserExibitionDto(
        String id,
        String nome,
        String email,
        LocalDateTime createDate,
        LocalDateTime modifiedDate,
        String cpf,
        String role
) {
    public UserExibitionDto(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreateDate(),
                user.getModifiedDate(),
                user.getCpf(),
                user.getRole().getDescription()
        );
    }

    public User toEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

}
