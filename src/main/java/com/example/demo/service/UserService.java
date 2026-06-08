package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {

        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    public UserDTO saveUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    // public UserDTO updateUser(UserDTO userDTO) {
    //     userRepo.save(modelMapper.map(userDTO,User.class));
    //     return userDTO;
    // }
    public UserDTO updateUser(UserDTO userDTO) {

        if (!userRepo.existsById(userDTO.getId())) {
            throw new RuntimeException("User not found");
        }

        User user = modelMapper.map(userDTO, User.class);
        User updatedUser = userRepo.save(user);

        return modelMapper.map(updatedUser, UserDTO.class);
    }

    // public String deleteUser(UserDTO userDTO) {
    //     userRepo.delete(modelMapper.map(userDTO, User.class));
    //     return "User deleted successfully";
    // }
    public String deleteUser(int id) {

        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepo.deleteById(id);

        return "User deleted successfully";
    }

    public UserDTO getUserById(int id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return modelMapper.map(user, UserDTO.class);
    }

}
