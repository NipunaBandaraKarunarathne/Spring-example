package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<UserDTO> getUsers() {

        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {

        return userService.saveUser(userDTO);
    }

    // @PutMapping("/updateuser")
    // public UserDTO updateUser(@RequestBody UserDTO userDTO) {
    //     return userService.updateUser(userDTO);
    // }
    @PutMapping("/updateuser/{id}")
    public UserDTO updateUser(
            @PathVariable Integer id,
            @RequestBody UserDTO userDTO) {

        userDTO.setId(id);

        return userService.updateUser(userDTO);
    }

    // @DeleteMapping("/deleteuser")
    // public String deleteUser( @RequestBody UserDTO userDTO) {
    //     return userService.deleteUser(userDTO);
    // }
    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

}
