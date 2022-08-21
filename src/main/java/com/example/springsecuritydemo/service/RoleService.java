package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.Role;
import com.example.springsecuritydemo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository repository;

    public List<Role> findAll(){
        return repository.findAll();
    }

    public Role save(Role role){
        return repository.save(role);
    }
}
