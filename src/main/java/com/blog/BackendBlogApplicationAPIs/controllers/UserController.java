package com.blog.BackendBlogApplicationAPIs.controllers;

import com.blog.BackendBlogApplicationAPIs.payloads.ApiResponse;
import com.blog.BackendBlogApplicationAPIs.payloads.UserDTO;
import com.blog.BackendBlogApplicationAPIs.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Authorization")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createUserDTO = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }
    @PutMapping("/{userID}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable Integer userID){
        UserDTO updateUser = this.userService.updateUser(userDTO,userID);
        return ResponseEntity.ok(updateUser);
    }

    //ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userID){
        this.userService.deleteUser(userID);
        return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userID){
        UserDTO userDTO = this.userService.getUserById(userID);
        return  ResponseEntity.ok(userDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}
