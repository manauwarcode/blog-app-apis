package com.blog.BackendBlogApplicationAPIs.services;

import com.blog.BackendBlogApplicationAPIs.entities.Post;
import com.blog.BackendBlogApplicationAPIs.payloads.PostDTO;
import com.blog.BackendBlogApplicationAPIs.payloads.PostResponse;
import com.blog.BackendBlogApplicationAPIs.payloads.UserDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
    PostDTO updatePost(PostDTO postDTO,Integer postID);

    void deletePost(Integer postID);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDirection);
    PostDTO getPostById(Integer postID);

    List<PostDTO> getPostsByCategory(Integer categoryId);
    PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize);

    List<PostDTO> searchPosts(String keyword);
}
