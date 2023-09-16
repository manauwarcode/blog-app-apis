package com.blog.BackendBlogApplicationAPIs.services.impl;

import com.blog.BackendBlogApplicationAPIs.entities.Comment;
import com.blog.BackendBlogApplicationAPIs.entities.Post;
import com.blog.BackendBlogApplicationAPIs.exceptions.ResourceNotFoundException;
import com.blog.BackendBlogApplicationAPIs.payloads.CommentDTO;
import com.blog.BackendBlogApplicationAPIs.payloads.PostDTO;
import com.blog.BackendBlogApplicationAPIs.payloads.PostResponse;
import com.blog.BackendBlogApplicationAPIs.repositories.CommentRepo;
import com.blog.BackendBlogApplicationAPIs.repositories.PostRepo;
import com.blog.BackendBlogApplicationAPIs.services.CommentService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postID) {
        Post post = this.postRepo.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post"," id ",postID));
        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentID) {
        Comment comment = this.commentRepo.findById(commentID).orElseThrow(() -> new ResourceNotFoundException("Comment", " id ",commentID));
        this.commentRepo.delete(comment);
    }
}
