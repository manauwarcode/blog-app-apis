package com.blog.BackendBlogApplicationAPIs.repositories;

import com.blog.BackendBlogApplicationAPIs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
