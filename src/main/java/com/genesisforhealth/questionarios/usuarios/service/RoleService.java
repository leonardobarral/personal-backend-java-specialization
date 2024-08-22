package com.genesisforhealth.questionarios.usuarios.service;

import com.genesisforhealth.questionarios.usuarios.dto.RoleAtualizationDto;
import com.genesisforhealth.questionarios.usuarios.dto.RoleCreationDto;
import com.genesisforhealth.questionarios.usuarios.dto.RoleExibitionDto;
import com.genesisforhealth.questionarios.usuarios.execption.ObjetoNaoEncontradoException;
import com.genesisforhealth.questionarios.usuarios.model.Authority;
import com.genesisforhealth.questionarios.usuarios.model.Role;
import com.genesisforhealth.questionarios.usuarios.repository.AuthorityRepository;
import com.genesisforhealth.questionarios.usuarios.repository.RoleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    public RoleExibitionDto gravar(RoleCreationDto roleCreationDto) {
        StringBuilder alerta = new StringBuilder("As seguintes authorities ainda não estão cadastradas: ");
        Role role = new Role();
        BeanUtils.copyProperties(roleCreationDto, role);
        List<Authority> authorities = roleCreationDto.authorities().stream().map(id -> {
            Optional<Authority> authority = authorityRepository.findById(id);
            if (authority.isPresent()) return authority.get();
            else {
                alerta.append(id).append(" ");
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        if (authorities.size() == roleCreationDto.authorities().size()) {
            role.setAuthorities(authorities);
            return new RoleExibitionDto(repository.save(role));
        } else {
            throw new ObjetoNaoEncontradoException(alerta.toString().trim());
        }
    }

    @Transactional
    public RoleExibitionDto getById(String id) {
        Optional<Role> role = repository.findById(id);
        if (role.isPresent())return new RoleExibitionDto(role.get());
        else throw new ObjetoNaoEncontradoException("Role não encotrada");
    }

    @Transactional
    public List<RoleExibitionDto> getAll() {
        List<RoleExibitionDto> roleExibitionDto = repository
                .findAll()
                .stream()
                .map(RoleExibitionDto::new)
                .toList();
        if (!roleExibitionDto.isEmpty()) return roleExibitionDto;
        else throw new ObjetoNaoEncontradoException("Nenhuma Role não encotrada");
    }

    @Transactional
    public RoleExibitionDto update(RoleAtualizationDto roleAtualizationDto) {
        Optional<Role> roleEncontrado = repository.findById(roleAtualizationDto.id());
        if (roleEncontrado.isPresent()) {
            Role roleupdate = roleEncontrado.get();
            if(roleAtualizationDto.description() != null) roleupdate.setDescription(roleAtualizationDto.description());
            if(roleAtualizationDto.observation() != null) roleupdate.setObservation(roleAtualizationDto.observation());
            if (roleAtualizationDto.authorities() != null) {
                StringBuilder alerta = new StringBuilder("As seguintes authorities ainda não estão cadastradas: ");
                List<Authority> authorities = roleAtualizationDto.authorities().stream().map(id -> {
                    Optional<Authority> authority = authorityRepository.findById(id);
                    if (authority.isPresent()) return authority.get();
                    else {
                        alerta.append(id).append(" ");
                        return null;
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());

                if (authorities.size() == roleAtualizationDto.authorities().size()) {
                    roleupdate.setAuthorities(authorities);
                } else throw new ObjetoNaoEncontradoException(alerta.toString().trim());
            }
            return new RoleExibitionDto(repository.save(roleupdate));
        } else throw new ObjetoNaoEncontradoException("Role não encotrada");
    }

    @Transactional
    public Boolean delete(String id) {
        Optional<Role> roleEncontrado = repository.findById(id);
        if (roleEncontrado.isPresent()) {
            var role = roleEncontrado.get();
            if (role.getUserList().isEmpty()) {
                if(!role.getAuthorities().isEmpty()) {
                    role.getAuthorities().clear();
                    repository.save(role);
                }
                repository.delete(role);
                return true;
            } else throw new ObjetoNaoEncontradoException("Role associado a usuarios existentes");
        } else throw new ObjetoNaoEncontradoException("Role não encotrada");
    }

}
