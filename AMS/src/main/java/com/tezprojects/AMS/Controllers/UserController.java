package com.tezprojects.AMS.Controllers;

import com.tezprojects.AMS.Models.UsersModel;
import com.tezprojects.AMS.Repositories.UsersRepository;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/Users")
@Log
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody @NotNull UsersModel user)
    {
        if(user.getId() == null || user.getName() == null || user.getProfession() == null)
        {
            log.info("Null Data Passed for POST Request");
            return ResponseEntity.badRequest().build();
        }
        UsersModel responseUserSave = this.usersRepository.save(user);
        log.info("Registered New User : " + responseUserSave.getName());
        return ResponseEntity.ok(responseUserSave);
    }

    @GetMapping("/")
    public ResponseEntity<?> getUser()
    {
        log.info("Retrieved User from Database");
        return ResponseEntity.ok(this.usersRepository.findAll());
    }

}
