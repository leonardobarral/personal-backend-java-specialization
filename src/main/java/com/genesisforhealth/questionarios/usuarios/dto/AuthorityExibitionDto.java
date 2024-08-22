package com.genesisforhealth.questionarios.usuarios.dto;

import com.genesisforhealth.questionarios.usuarios.model.Authority;
import com.genesisforhealth.questionarios.usuarios.model.Role;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;


public record AuthorityExibitionDto(
        String id,
        String authority,
        String observation,
        List<String> roles,
        LocalDateTime createDate,
        LocalDateTime modifiedDate

) {
    public AuthorityExibitionDto(Authority authority) {
        this(
                authority.getId(),
                authority.getAuthority(),
                authority.getObservation(),
                authority.getRoles().stream().map(Role::getDescription).toList(),
                authority.getCreateDate(),
                authority.getModifiedDate()
        );
    }

    public Authority toEntity() {
        Authority authority = new Authority();
        BeanUtils.copyProperties(this, authority);
        return authority;
    }
}
