package com.blog.BackendBlogApplicationAPIs.services.impl;

import com.blog.BackendBlogApplicationAPIs.entities.Category;
import com.blog.BackendBlogApplicationAPIs.exceptions.ResourceNotFoundException;
import com.blog.BackendBlogApplicationAPIs.payloads.CategoryDTO;
import com.blog.BackendBlogApplicationAPIs.repositories.CategoryRepo;
import com.blog.BackendBlogApplicationAPIs.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
       Category category = this.modelMapper.map(categoryDTO, Category.class);
       Category savedCategory = this.categoryRepo.save(category);
       return this.modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " id ",categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDesc(category.getCategoryDesc());
        Category updatedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDTO.class);

    }

    @Override
    public void deleteCategory(Integer categoryId){
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Categoty", " id ",categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Categoty", " id ",categoryId));
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> allCategory = this.categoryRepo.findAll();
        List<CategoryDTO> allCategoryDTO = allCategory.stream().map(category -> this.modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
        return allCategoryDTO;
    }
}
