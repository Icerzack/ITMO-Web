package com.lab4.Controllers;

import com.lab4.Repos.RoleRepository;
import com.lab4.Models.Role;
import com.lab4.Models.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initController(){
        if(roleRepository.findAll().size() == 0){
            roleRepository.save(new Role(RoleEnum.USER.getName()));
            roleRepository.save(new Role(RoleEnum.ADMINISTRATOR.getName()));
        }
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getRoles() {
        initController();
        return ResponseEntity.badRequest().body(roleRepository.findAll().stream().map(x -> x.toString()));
    }
}
