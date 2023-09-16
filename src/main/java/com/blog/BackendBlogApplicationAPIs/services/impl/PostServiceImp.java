package com.blog.BackendBlogApplicationAPIs.services.impl;

import com.blog.BackendBlogApplicationAPIs.entities.Category;
import com.blog.BackendBlogApplicationAPIs.entities.Post;
import com.blog.BackendBlogApplicationAPIs.entities.User;
import com.blog.BackendBlogApplicationAPIs.exceptions.ResourceNotFoundException;
import com.blog.BackendBlogApplicationAPIs.payloads.PostDTO;
import com.blog.BackendBlogApplicationAPIs.payloads.PostResponse;
import com.blog.BackendBlogApplicationAPIs.repositories.CategoryRepo;
import com.blog.BackendBlogApplicationAPIs.repositories.PostRepo;
import com.blog.BackendBlogApplicationAPIs.repositories.UserRepo;
import com.blog.BackendBlogApplicationAPIs.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " id ",categoryId));
        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = this.postRepo.save(post);
        return this.modelMapper.map(savedPost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postID){
        Post post = this.postRepo.findById(postID).orElseThrow(()-> new ResourceNotFoundException(" Post ", " id ",postID));
        post.setImageName(postDTO.getImageName());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDTO.class);
    }

    @Override
    public void deletePost(Integer postID) {
        Post post = this.postRepo.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", " id ",postID));
        this.postRepo.delete(post);
    }

    // this method will getALl the post with pagination
    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPost = pagePost.getContent();

        List<PostDTO> postDTOS = allPost.stream().map((post -> this.modelMapper.map(post,PostDTO.class))).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postID) {
        Post post = this.postRepo.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post"," id ",postID));
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " id ",categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDTO> listOfPostDTO = posts.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).collect(Collectors.toList());
        return listOfPostDTO;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ",userId));
        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<Post> allPostByUser = this.postRepo.findAllByUser(user,p);
        List<Post> allPost = allPostByUser.getContent();
        List<PostDTO> listOfPostDto = allPost.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(listOfPostDto);
        postResponse.setPageNumber(allPostByUser.getNumber());
        postResponse.setPageSize(allPostByUser.getSize());
        postResponse.setTotalPages(allPostByUser.getTotalPages());
        postResponse.setTotalElements(allPostByUser.getTotalElements());
        postResponse.setLastPage(allPostByUser.isLast());
        return postResponse;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDTO> postDTOS = posts.stream().map((post -> this.modelMapper.map(post,PostDTO.class))).collect(Collectors.toList());
        return postDTOS;
    }
}
