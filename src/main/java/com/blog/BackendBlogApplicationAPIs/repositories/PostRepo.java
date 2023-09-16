package com.blog.BackendBlogApplicationAPIs.repositories;

import com.blog.BackendBlogApplicationAPIs.entities.Category;
import com.blog.BackendBlogApplicationAPIs.entities.Post;
import com.blog.BackendBlogApplicationAPIs.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    //Custom finder methods
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    Page<Post> findAllByUser(User user, Pageable pageable);

    List<Post> findByTitleContaining(String title);
}
