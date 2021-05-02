package com.dterz.controllers;

import com.dterz.model.User;
import com.dterz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getUserById/{paramId}")
    public User getUserById(@PathVariable("paramId") long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("update")
    public User update(@RequestBody User user) {
        return userService.updateUser(user);
    }

}
