package com.blog.BackendBlogApplicationAPIs.repositories;

import com.blog.BackendBlogApplicationAPIs.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
