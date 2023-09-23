package com.blog.BackendBlogApplicationAPIs.services.impl;

import com.blog.BackendBlogApplicationAPIs.entities.Role;
import com.blog.BackendBlogApplicationAPIs.exceptions.ResourceNotFoundException;
import com.blog.BackendBlogApplicationAPIs.payloads.RoleDTO;
import com.blog.BackendBlogApplicationAPIs.repositories.RoleRepo;
import com.blog.BackendBlogApplicationAPIs.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deleteRole(Integer roleID) {
        Role role = this.roleRepo.findById(roleID).orElseThrow(() -> new ResourceNotFoundException("Role", " id ",roleID));
        this.roleRepo.delete(role);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = this.roleRepo.findAll();
        List<RoleDTO> roleDTOS = roles.stream().map(role -> this.modelMapper.map(role,RoleDTO.class)).collect(Collectors.toList());
        return roleDTOS;
    }
}
