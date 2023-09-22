package com.blog.BackendBlogApplicationAPIs.services.impl;

import com.blog.BackendBlogApplicationAPIs.config.AppConstants;
import com.blog.BackendBlogApplicationAPIs.entities.Role;
import com.blog.BackendBlogApplicationAPIs.entities.User;
import com.blog.BackendBlogApplicationAPIs.exceptions.ResourceNotFoundException;
import com.blog.BackendBlogApplicationAPIs.payloads.UserDTO;
import com.blog.BackendBlogApplicationAPIs.repositories.RoleRepo;
import com.blog.BackendBlogApplicationAPIs.repositories.UserRepo;
import com.blog.BackendBlogApplicationAPIs.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
       User user = this.dtoToUser(userDTO);
       User savedUser = this.userRepo.save(user);
       return this.userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userID) {
        User user = this.userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User"," id ",userID));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        User updatedUser = this.userRepo.save(user);
        return this.userToDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userID) {
        User user = this.userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", " id ",userID));
        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOS = users.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void deleteUser(Integer userID) {
        User user = this.userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", " id " , userID));
        this.userRepo.delete(user);
    }
    public User dtoToUser(UserDTO userdto){
       User user = this.modelMapper.map(userdto,User.class);
       return user;
    }

    public UserDTO userToDTO(User user){
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
        return userDTO;
    }

    public UserDTO registerNewUser(UserDTO userDTO){
        User user = this.modelMapper.map(userDTO,User.class);
        String password = user.getPassword();
        password = password == null ? "default" : password;
        user.setPassword(this.passwordEncoder.encode(password));
        Role role = this.roleRepo.findById(AppConstants.ROLE_USER).get();
        user.getRoles().add(role);
        User savedUser = this.userRepo.save(user);
        return this.modelMapper.map(savedUser,UserDTO.class);
    }
}