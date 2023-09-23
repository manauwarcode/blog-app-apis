package com.blog.BackendBlogApplicationAPIs.services;

import com.blog.BackendBlogApplicationAPIs.payloads.RoleDTO;

import java.util.List;

public interface RoleService {

    void deleteRole(Integer roleID);
    List<RoleDTO> getAllRoles();


}
