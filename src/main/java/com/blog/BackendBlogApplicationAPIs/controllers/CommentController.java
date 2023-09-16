package com.blog.BackendBlogApplicationAPIs.controllers;

import com.blog.BackendBlogApplicationAPIs.entities.Comment;
import com.blog.BackendBlogApplicationAPIs.payloads.ApiResponse;
import com.blog.BackendBlogApplicationAPIs.payloads.CommentDTO;
import com.blog.BackendBlogApplicationAPIs.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postID}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postID){
        CommentDTO commentDTO1 = this.commentService.createComment(commentDTO,postID);
        return new ResponseEntity<>(commentDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentID}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentID){
        this.commentService.deleteComment(commentID);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully",true),HttpStatus.OK);
    }


}
