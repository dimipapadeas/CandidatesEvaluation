package com.dterz.controllers;

import com.dterz.dtos.UserDTO;
import com.dterz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("getAll")
    public List<UserDTO> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("getUserById/{userId}")
    public UserDTO getUserById(@PathVariable("userId") long userId) {
        logger.debug("Called: getuser with {}", userId);
        return userService.getUserById(userId);
    }

    @PutMapping("update")
    public UserDTO update(@RequestBody UserDTO user) {
        return userService.updateUser(user);
    }

    @GetMapping("createDraftUser")
    public UserDTO createDraftUser() {
        return userService.createDraftUser();
    }

    @DeleteMapping("delete/{userId}")
    public void delete(@PathVariable("userId") long userId) {
        userService.deleteUser(userId);
    }
}
