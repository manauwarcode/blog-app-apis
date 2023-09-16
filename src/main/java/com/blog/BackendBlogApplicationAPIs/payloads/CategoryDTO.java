package com.blog.BackendBlogApplicationAPIs.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Integer categoryId;
    @NotEmpty
    @Size(min=4, message = "Category title must not be blank as well as it should be of size at least 4 characters.")
    private String categoryTitle;
    @NotEmpty
    @Size(min=10, message = "Category description must not be blank as well as it should be of size at least 10 characters.")
    private String categoryDesc;
}
