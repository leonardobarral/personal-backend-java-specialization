package com.genesisforhealth.questionarios.usuarios.service;


import com.genesisforhealth.questionarios.usuarios.dto.RoleExibitionDto;
import com.genesisforhealth.questionarios.usuarios.dto.UserCreationDto;
import com.genesisforhealth.questionarios.usuarios.dto.UserExibitionDto;
import com.genesisforhealth.questionarios.usuarios.dto.UserUpdateDto;
import com.genesisforhealth.questionarios.usuarios.execption.ObjetoNaoEncontradoException;
import com.genesisforhealth.questionarios.usuarios.model.Role;
import com.genesisforhealth.questionarios.usuarios.model.User;
import com.genesisforhealth.questionarios.usuarios.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleService roleService;

    @Transactional
    public UserExibitionDto gravar(UserCreationDto userCreationDto) {
        String passwordCriptografada = new BCryptPasswordEncoder().encode(userCreationDto.password());
        RoleExibitionDto roleExibitionDto = roleService.getById(userCreationDto.role_id());

        User user = new User();
        BeanUtils.copyProperties(userCreationDto, user);

        user.setPassword(passwordCriptografada);

        if (roleExibitionDto != null) {
            user.setRole(roleExibitionDto.toEntity());
            return new UserExibitionDto(repository.save(user));
        } else {
            throw new ObjetoNaoEncontradoException("Regra não encotrada");
        }
    }

    @Transactional
    public UserExibitionDto getById(String id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) return new UserExibitionDto(user.get());
        else throw new ObjetoNaoEncontradoException("Usuário não encotrado");
    }

    @Transactional
    public List<UserExibitionDto> getAll() {
        List<UserExibitionDto> users = repository
                .findAll()
                .stream()
                .map(UserExibitionDto::new)
                .toList();
        if (!users.isEmpty()) return users;
        else throw new ObjetoNaoEncontradoException("Nenhum usuário encotrado");
    }

    @Transactional
    public UserExibitionDto update(UserUpdateDto userUpdateDto) {
        Optional<User> userEncontrado = repository.findById(userUpdateDto.id());
        if (userEncontrado.isPresent()) {
            User user = userEncontrado.get();
            if (userUpdateDto.name() != null) user.setName(userUpdateDto.name());
            if (userUpdateDto.cpf() != null) user.setCpf(userUpdateDto.cpf());
            if (userUpdateDto.email() != null) user.setEmail(userUpdateDto.email());
            if (userUpdateDto.password() != null){
                String passwordCriptografada = new BCryptPasswordEncoder().encode(userUpdateDto.password());
                user.setPassword(passwordCriptografada);
            }
            if(userUpdateDto.role_id()!= null){
                RoleExibitionDto roleExibitionDto = roleService.getById(userUpdateDto.role_id());
                if (roleExibitionDto != null) {
                    user.setRole(roleExibitionDto.toEntity());
                    return new UserExibitionDto(repository.save(user));
                }else throw new ObjetoNaoEncontradoException("Regra não encotrada");
            }else return new UserExibitionDto(repository.save(user));
        } else throw new ObjetoNaoEncontradoException("Usuário não encotrado");
    }

    @Transactional
    public Boolean delete(String id) {
        Optional<User> userEncontrado = repository.findById(id);
        if (userEncontrado.isPresent()) {
            repository.delete(userEncontrado.get());
            return true;
        } else throw new ObjetoNaoEncontradoException("Usuário não encotrado");
    }

}
