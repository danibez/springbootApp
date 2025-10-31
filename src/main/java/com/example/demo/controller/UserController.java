package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUser/{id}")
    public UserModel getMethodName(@PathVariable long id) {
        return userService.getUserById(id);
    }
    

    @GetMapping("/getAllUsers")
    public List<UserResponseDTO> getAllUSers(){
        List<UserModel> response = userService.getAllUsers();
        List<UserResponseDTO> responseDTO = new ArrayList<UserResponseDTO>();
        for (int i = 0; i < response.size(); i++) {
            responseDTO.add(new UserResponseDTO(response.get(i).getId(), response.get(i).getUsername(), response.get(i).getAge(), response.get(i).getPets()));
        }
        return responseDTO;
    }

    @PostMapping("/newUser")
    public UserResponseDTO addNewUser(@RequestBody UserRequestDTO newUser){
        UserModel newUsermodel = new UserModel();
        newUsermodel.setAge(newUser.getAge());
        newUsermodel.setPassword(newUser.getPassword());
        newUsermodel.setUsername(newUser.getUsername());

        UserModel response = userService.createUser(newUsermodel);
    
        UserResponseDTO respDTO = new UserResponseDTO(response.getId(), response.getUsername(), response.getAge(), response.getPets());

        return respDTO;
    }
    
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!!!";
    }
}
