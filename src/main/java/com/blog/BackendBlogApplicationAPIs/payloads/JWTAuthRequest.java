package com.blog.BackendBlogApplicationAPIs.payloads;

import lombok.Data;

@Data
public class JWTAuthRequest {

    private String username;
    private String password;
}
