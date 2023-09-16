package com.blog.BackendBlogApplicationAPIs.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    long fieldValue;
    String fieldValueWithString;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue){
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueWithString){
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValueWithString));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueWithString = fieldValueWithString;
    }
}
