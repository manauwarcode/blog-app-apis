package com.blog.BackendBlogApplicationAPIs.controllers;

import com.blog.BackendBlogApplicationAPIs.payloads.ApiResponse;
import com.blog.BackendBlogApplicationAPIs.payloads.CategoryDTO;
import com.blog.BackendBlogApplicationAPIs.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1 = this.categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(categoryDTO1);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
        CategoryDTO categoryDTO1 = this.categoryService.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(categoryDTO1,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId){
        CategoryDTO categoryDTO = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> allCategory = this.categoryService.getAllCategory();
        return new ResponseEntity<>(allCategory,HttpStatus.OK);
    }
}
