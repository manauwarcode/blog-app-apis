package com.blog.BackendBlogApplicationAPIs.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;
    @NotEmpty
    @Size(min=4,message = "User name must be minimum of 4 characters !!")
    private String name;
    @Email(message = "Your email address is not valid.")
    private String email;
    @NotEmpty
    @Size(min=3, max = 15, message = "Password must be of at least 3 chars and at most 15 chars")
    private String password;
    @NotEmpty
    private String about;

}
