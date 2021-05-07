package com.dterz.controllers;

import com.dterz.dtos.UserDTO;
import com.dterz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getAll")
    public List<UserDTO> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("getUserById/{userId}")
    public UserDTO getUserById(@PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("update")
    public UserDTO update(@RequestBody UserDTO user) {
        return userService.updateUser(user);
    }

    @GetMapping("createDraftUser")
    public UserDTO createDraftUser() {
        return userService.createDraftUser(7);
    }

    @PutMapping("storeNewUser")
    public UserDTO storeNewUser(@RequestBody UserDTO user) {
        return userService.storeNewUser(user);
    }

    @DeleteMapping("delete/{userId}")
    public void delete(@PathVariable("userId") long userId) {
        userService.deleteUser(userId);
    }
}
