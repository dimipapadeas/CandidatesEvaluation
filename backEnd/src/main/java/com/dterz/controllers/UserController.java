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

    /**
     * Gets all the available Users currently in the System
     *
     * @return List<UserDTO>
     */
    @GetMapping("getAll")
    public List<UserDTO> getUser() {
        return userService.getAllUsers();
    }

    /**
     * Gets a User by its id
     *
     * @param userId the id of the User requested
     * @return UserDTO
     */
    @GetMapping("getUserById/{userId}")
    public UserDTO getUserById(@PathVariable("userId") long userId) {
        log.debug("Called: getuser with {}", userId);
        return userService.getUserById(userId);
    }

    /**
     * Updates the User with the data from the Front end
     *
     * @param user the updated User
     * @return UserDTO
     */
    @PutMapping("update")
    public UserDTO update(@RequestBody UserDTO user) {
        return userService.updateUser(user);
    }

    /**
     * Creates and returns a new empty User Object
     *
     * @return UserDTO
     */
    @GetMapping("createDraftUser")
    public UserDTO createDraftUser() {
        return userService.createDraftUser();
    }

    /**
     * Deletes an User from the Database
     *
     * @param userId the id of the User to delete
     */
    @DeleteMapping("delete/{userId}")
    public void delete(@PathVariable("userId") long userId) {
        userService.deleteUser(userId);
    }
}
