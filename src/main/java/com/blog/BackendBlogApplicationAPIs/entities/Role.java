package com.blog.BackendBlogApplicationAPIs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.StandardException;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    private Integer id;
    private  String name;
}
