package com.blog.BackendBlogApplicationAPIs.repositories;

import com.blog.BackendBlogApplicationAPIs.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
