package com.blog.BackendBlogApplicationAPIs.controllers;

import com.blog.BackendBlogApplicationAPIs.config.AppConstants;
import com.blog.BackendBlogApplicationAPIs.payloads.ApiResponse;
import com.blog.BackendBlogApplicationAPIs.payloads.PostDTO;
import com.blog.BackendBlogApplicationAPIs.payloads.PostResponse;
import com.blog.BackendBlogApplicationAPIs.services.FileService;
import com.blog.BackendBlogApplicationAPIs.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
@SecurityRequirement(name = "Authorization")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDTO postDTO1 = this.postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<PostDTO>(postDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postID}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Integer postID){
        PostDTO postDTO1 = this.postService.updatePost(postDTO,postID);
        return new ResponseEntity<>(postDTO1,HttpStatus.OK);
    }

    @GetMapping("/user/{userID}/posts")
    public ResponseEntity<PostResponse> getAllPostByUser(@PathVariable Integer userID,@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize){
        PostResponse posts = this.postService.getPostsByUser(userID,pageNumber,pageSize);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryID}/posts")
    public ResponseEntity<List<PostDTO>> getAllPostByCategory(@PathVariable Integer categoryID){
        List<PostDTO> posts = this.postService.getPostsByCategory(categoryID);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    // Usage: http://localhost:9090/api/posts?pageNumber=1&pageSize=2
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value= "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize, @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy, @RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIR,required = false)String sortDirection){
        PostResponse posts = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts/{postID}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable Integer postID){
        PostDTO post = this.postService.getPostById(postID);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @DeleteMapping("posts/{postID}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postID){
        this.postService.deletePost(postID);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keywords){
        List<PostDTO> postDTOS = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }
    @PostMapping("/post/image/upload/{postID}")
    public ResponseEntity<PostDTO> uploadPostImage(@RequestParam MultipartFile image,@PathVariable Integer postID) throws IOException {
        PostDTO postDTO = this.postService.getPostById(postID);
        String fileName = this.fileService.uploadImage(path,image);
        postDTO.setImageName(fileName);
        PostDTO updatedPost = this.postService.updatePost(postDTO,postID);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
