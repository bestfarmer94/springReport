package com.sparta.springreport.entity;

import com.sparta.springreport.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    public Post(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.author = postRequestDto.getAuthor();
        this.contents = postRequestDto.getContents();
        this.password = postRequestDto.getPassword();
    }
}
