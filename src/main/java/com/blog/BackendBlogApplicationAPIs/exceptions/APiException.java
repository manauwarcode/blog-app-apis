package com.blog.BackendBlogApplicationAPIs.exceptions;

public class APiException extends RuntimeException{
    public APiException() {
    }

    public APiException(String message) {
        super(message);
    }
}
