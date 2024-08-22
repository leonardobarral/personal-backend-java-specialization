package com.genesisforhealth.questionarios.usuarios.controller;

import com.genesisforhealth.questionarios.usuarios.config.security.TokenService;
import com.genesisforhealth.questionarios.usuarios.dto.TokenDto;
import com.genesisforhealth.questionarios.usuarios.dto.UserCreationDto;
import com.genesisforhealth.questionarios.usuarios.dto.UserExibitionDto;
import com.genesisforhealth.questionarios.usuarios.dto.UserLoginDto;
import com.genesisforhealth.questionarios.usuarios.model.User;
import com.genesisforhealth.questionarios.usuarios.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDto dto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserExibitionDto userExibitionDtoRegister(@RequestBody @Valid UserCreationDto userCreationDto) {
        UserExibitionDto userSalved = null;
        userSalved = userService.gravar(userCreationDto);
        return userSalved;
    }
}
