package com.dterz.service;

import com.dterz.Constants;
import com.dterz.dtos.UserDTO;
import com.dterz.mappers.UserMapper;
import com.dterz.model.User;
import com.dterz.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return mapper.entityListToDTOList(userList);
    }

    public UserDTO getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return mapper.entityToDto(user.get());
    }

    public UserDTO updateUser(UserDTO dto) {
        User user;
        Optional<User> oUser = userRepository.findById(dto.getId());
        if (oUser.isPresent()) {
            user = oUser.get();
            mapper.dtoToEntity(dto, user);
        } else {
            user = mapper.dtoToEntity(dto);
        }
        userRepository.save(user);
        return mapper.entityToDto(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public UserDTO createDraftUser(int count) {
        User draftUser = new User();
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * Constants.ALPHA_NUMERIC_STRING.length());
            builder.append(Constants.ALPHA_NUMERIC_STRING.charAt(character));
        }
        draftUser.setSalt(builder.toString());
        return mapper.entityToDto(draftUser);
    }

    public UserDTO storeNewUser(UserDTO user) {
        user.setPass(DigestUtils.sha256Hex(user.getSalt() + user.getPass()));
        return this.updateUser(user);
    }

}

