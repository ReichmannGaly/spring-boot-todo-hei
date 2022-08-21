package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.UserDetails;
import com.example.springsecuritydemo.repository.UserDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsService {
    private UserDetailsRepository repository;

    public List<UserDetails> findAll(){
        return repository.findAll();
    }

    public UserDetails save(UserDetails userDetails){
        return repository.save(userDetails);
    }
}
