package com.dterz.controllers;

import com.dterz.model.User;
import com.dterz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getAll")
    public List<User> getUser() {
        return userService.getAllUsers();
    }

}
