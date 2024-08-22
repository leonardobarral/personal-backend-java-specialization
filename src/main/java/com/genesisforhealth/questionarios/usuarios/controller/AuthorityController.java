package com.genesisforhealth.questionarios.usuarios.controller;

import com.genesisforhealth.questionarios.usuarios.dto.AuthorityCreationDto;
import com.genesisforhealth.questionarios.usuarios.dto.AuthorityExibitionDto;
import com.genesisforhealth.questionarios.usuarios.model.Authority;
import com.genesisforhealth.questionarios.usuarios.service.AuthorityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class AuthorityController {

    @Autowired
    private AuthorityService service;

    @PostMapping("/authority")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorityExibitionDto gravar(@RequestBody @Valid AuthorityCreationDto authorityCreationDto) {
        return service.gravar(authorityCreationDto);
    }

    @GetMapping("/authority/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorityExibitionDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/authority")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorityExibitionDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/authority")
    @ResponseStatus(HttpStatus.OK)
    public AuthorityExibitionDto update(@RequestBody Authority authority) {
        return service.update(authority);
    }

    @DeleteMapping("/authority/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean delete(@PathVariable String id) {
        return service.delete(id);
    }
}
