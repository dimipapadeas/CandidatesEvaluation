package com.dterz.service;

import com.dterz.Constants;
import com.dterz.dtos.UserDTO;
import com.dterz.mappers.UserMapper;
import com.dterz.model.User;
import com.dterz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return mapper.entityListToDTOList(userList);
    }

    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id).get();
        return mapper.entityToDto(user);
    }

    public UserDTO updateUser(UserDTO dto) {
        User user = userRepository.findById(dto.getId()).get();
        if (user != null) {
            mapper.dtoToEntity(dto, user);
        } else {
            user = mapper.dtoToEntity(dto);
            user.setPass(DigestUtils.sha256Hex(dto.getSalt() + dto.getPass()));
        }
        userRepository.save(user);
        return mapper.entityToDto(user);
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    public UserDTO createDraftUser() {
        User draftUser = new User();
        draftUser.setSalt(createSalt());
        return mapper.entityToDto(draftUser);
    }

    public UserDTO storeNewUser(UserDTO user) {
        user.setPass(DigestUtils.sha256Hex(user.getSalt() + user.getPass()));
        return this.updateUser(user);
    }


    private String createSalt() {
        int count = 7;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * Constants.ALPHA_NUMERIC_STRING.length());
            builder.append(Constants.ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}

