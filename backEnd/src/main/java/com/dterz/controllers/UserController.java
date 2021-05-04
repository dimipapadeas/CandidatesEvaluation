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

    @GetMapping("getUserById/{userId}")
    public User getUserById(@PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("update")
    public User update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("createDraftUser")
    public User createDraftUser() {
        return userService.createDraftUser(7);
    }

    @PutMapping("storeNewUser")
    public User storeNewUser(@RequestBody User user) {
        return userService.storeNewUser(user);
    }

    @DeleteMapping("delete/{userId}")
    public void delete(@PathVariable("userId") long userId) {
        userService.deleteUser(userId);
    }
}
