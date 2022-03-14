package com.dterz.controllers;

import com.dterz.dtos.UserDTO;
import com.dterz.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("getAll")
    public List<UserDTO> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("getUserById/{userId}")
    public UserDTO getUserById(@PathVariable("userId") long userId) {
        log.debug("Called: getuser with {}", userId);
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
