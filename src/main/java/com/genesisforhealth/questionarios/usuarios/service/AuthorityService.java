package com.genesisforhealth.questionarios.usuarios.service;

import com.genesisforhealth.questionarios.usuarios.dto.AuthorityCreationDto;
import com.genesisforhealth.questionarios.usuarios.dto.AuthorityExibitionDto;
import com.genesisforhealth.questionarios.usuarios.execption.ObjetoNaoEncontradoException;
import com.genesisforhealth.questionarios.usuarios.model.Authority;
import com.genesisforhealth.questionarios.usuarios.model.User;
import com.genesisforhealth.questionarios.usuarios.repository.AuthorityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository repository;

    @Transactional
    public AuthorityExibitionDto gravar(AuthorityCreationDto authorityCreationDto) {
        Authority authority = new Authority();
        BeanUtils.copyProperties(authorityCreationDto, authority);
        return new AuthorityExibitionDto(repository.save(authority));
    }

    @Transactional
    public AuthorityExibitionDto getById(String id) {
        Optional<Authority> authority = repository.findById(id);
        if (authority.isPresent()) return new AuthorityExibitionDto(authority.get());
        else throw new ObjetoNaoEncontradoException("Permissão não encotrada");
    }

    @Transactional
    public List<AuthorityExibitionDto> getAll() {
        List<AuthorityExibitionDto> authorityExibitionDto = repository
                .findAll()
                .stream()
                .map(AuthorityExibitionDto::new)
                .toList();
        if (!authorityExibitionDto.isEmpty()) return authorityExibitionDto;
        else throw new ObjetoNaoEncontradoException("Nenhum permissão encotrada");
    }

    @Transactional
    public AuthorityExibitionDto update(Authority authority) {
        Optional<Authority> authorityEncontrado = repository.findById(authority.getId());
        if (authorityEncontrado.isPresent()){
            Authority authorityUpdate = authorityEncontrado.get();
            if (authority.getAuthority() != null) authorityUpdate.setAuthority(authority.getAuthority());
            if (authority.getObservation() != null) authorityUpdate.setObservation(authority.getObservation());
            return new AuthorityExibitionDto(repository.save(authorityUpdate));
        }
        else throw new ObjetoNaoEncontradoException("Permissão não encotrada");
    }

    @Transactional
    public Boolean delete(String id) {
        Optional<Authority> authorityEncontrado = repository.findById(id);
        if (authorityEncontrado.isPresent()) {
            if (authorityEncontrado.get().getRoles().isEmpty()) {
                repository.delete(authorityEncontrado.get());
                return true;
            } else throw new ObjetoNaoEncontradoException("Permissão associada a regras existentes");
        } else throw new ObjetoNaoEncontradoException("Permissão não encotrada");
    }
}
