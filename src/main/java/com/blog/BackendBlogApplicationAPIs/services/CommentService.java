package com.blog.BackendBlogApplicationAPIs.services;

import com.blog.BackendBlogApplicationAPIs.payloads.CommentDTO;
import com.blog.BackendBlogApplicationAPIs.payloads.PostDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Integer postID);
    void deleteComment(Integer commentID);
}
