package com.blog.BackendBlogApplicationAPIs.services;

import com.blog.BackendBlogApplicationAPIs.entities.User;
import com.blog.BackendBlogApplicationAPIs.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Integer userID);

    UserDTO getUserById(Integer userID);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userID);
}
