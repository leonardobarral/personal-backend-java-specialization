package com.genesisforhealth.questionarios.usuarios.dto;

import com.genesisforhealth.questionarios.usuarios.model.Authority;
import com.genesisforhealth.questionarios.usuarios.model.Role;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;


public record RoleExibitionDto(

        String id,
        String description,
        String observation,
        LocalDateTime createDate,
        LocalDateTime modifiedDate,
        List<UserExibitionDto> userExibitionDtoList,
        List<String> authorityList
) {
    public RoleExibitionDto(Role role) {
        this(
                role.getId(),
                role.getDescription(),
                role.getObservation(),
                role.getCreateDate(),
                role.getModifiedDate(),
                role.getUserList().stream().map(UserExibitionDto::new).toList(),
                role.getAuthorities().stream().map(Authority::getAuthority).toList()
        );
    }

    public Role toEntity() {
        Role role = new Role();
        BeanUtils.copyProperties(this, role);
        return role;
    }
}
