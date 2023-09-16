package com.blog.BackendBlogApplicationAPIs.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name="post")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Integer postId;
    @Column(name="post_title",length = 100,nullable = false)
    private String title;
    @Column(name="post_content",length = 2000)
    private String content;
    @Column(name="post_image")
    private String imageName;
    @Column(name="post_add_date")
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> commentSet = new HashSet<>();

}
