package com.blog.BackendBlogApplicationAPIs.payloads;

import com.blog.BackendBlogApplicationAPIs.entities.Category;
import com.blog.BackendBlogApplicationAPIs.entities.Comment;
import com.blog.BackendBlogApplicationAPIs.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.PrimitiveIterator;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDTO user;
    private CategoryDTO category;
    private Set<CommentDTO> commentSet = new HashSet<>();
}
