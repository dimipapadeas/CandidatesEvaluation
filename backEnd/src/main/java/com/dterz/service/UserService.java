package com.dterz.service;

import com.dterz.Constants;
import com.dterz.model.User;
import com.dterz.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User createDraftUser(int count) {
        User draftUser = new User();
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * Constants.ALPHA_NUMERIC_STRING.length());
            builder.append(Constants.ALPHA_NUMERIC_STRING.charAt(character));
        }
        draftUser.setSalt(builder.toString());
        return draftUser;
    }

    public User storeNewUser(User user) {
        user.setPass(DigestUtils.sha256Hex(user.getSalt() + user.getPass()));
        return this.updateUser(user);
    }

}

