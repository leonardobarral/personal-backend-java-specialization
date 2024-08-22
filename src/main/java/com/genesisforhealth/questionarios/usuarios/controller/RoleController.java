package com.genesisforhealth.questionarios.usuarios.controller;

import com.genesisforhealth.questionarios.usuarios.dto.RoleAtualizationDto;
import com.genesisforhealth.questionarios.usuarios.dto.RoleCreationDto;
import com.genesisforhealth.questionarios.usuarios.dto.RoleExibitionDto;
import com.genesisforhealth.questionarios.usuarios.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/security")
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping("/role")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleExibitionDto gravar(@RequestBody @Valid RoleCreationDto roleCreationDto) {
        return service.gravar(roleCreationDto);
    }

    @GetMapping("/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleExibitionDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/role")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleExibitionDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/role")
    @ResponseStatus(HttpStatus.OK)
    public RoleExibitionDto update(@RequestBody RoleAtualizationDto roleAtualizationDto) {
        return service.update(roleAtualizationDto);
    }

    @DeleteMapping("/role/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean delete(@PathVariable String id) {
        return service.delete(id);
    }
}
