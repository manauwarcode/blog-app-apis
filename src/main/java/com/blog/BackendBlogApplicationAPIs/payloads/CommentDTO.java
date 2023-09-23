package com.blog.BackendBlogApplicationAPIs.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private Integer id;
    private String content;
}
