package com.blog.BackendBlogApplicationAPIs.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.Inet4Address;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private Integer id;
    private String content;
}
