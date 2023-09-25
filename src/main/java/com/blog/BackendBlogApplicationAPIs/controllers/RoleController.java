package com.blog.BackendBlogApplicationAPIs.controllers;
import com.blog.BackendBlogApplicationAPIs.payloads.ApiResponse;
import com.blog.BackendBlogApplicationAPIs.payloads.RoleDTO;
import com.blog.BackendBlogApplicationAPIs.services.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@SecurityRequirement(name = "Authorization")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles/")
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        List<RoleDTO> roleDTOS = this.roleService.getAllRoles();
        return new ResponseEntity<>(roleDTOS,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/roles/{roleID}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable Integer roleID){
        this.roleService.deleteRole(roleID);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Role deleted successfully.",true),HttpStatus.OK);
    }
}
