package com.blog.BackendBlogApplicationAPIs.services;

import com.blog.BackendBlogApplicationAPIs.payloads.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Integer postID);
    void deleteComment(Integer commentID);
}
