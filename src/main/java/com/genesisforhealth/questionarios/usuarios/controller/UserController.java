package com.genesisforhealth.questionarios.usuarios.controller;

import com.genesisforhealth.questionarios.usuarios.dto.UserExibitionDto;
import com.genesisforhealth.questionarios.usuarios.dto.UserUpdateDto;
import com.genesisforhealth.questionarios.usuarios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/security")
public class UserController {
    @Autowired
    private UserService service;

//    @PostMapping("/user")
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserExibitionDto gravar(@RequestBody @Valid UserCreationDto){
//        return service.gravar(userCreationDto);
//    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserExibitionDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserExibitionDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UserExibitionDto update(@RequestBody UserUpdateDto userUpdateDto) {
        return service.update(userUpdateDto);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean delete(@PathVariable String id) {
        return service.delete(id);
    }
}
