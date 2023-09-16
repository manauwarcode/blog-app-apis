package com.blog.BackendBlogApplicationAPIs.services;

import com.blog.BackendBlogApplicationAPIs.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
    void deleteCategory(Integer categoryId);
    CategoryDTO getCategoryById(Integer categoryId);
    List<CategoryDTO> getAllCategory();
}
