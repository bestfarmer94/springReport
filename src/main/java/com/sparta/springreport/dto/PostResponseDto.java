package com.sparta.springreport.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private LocalDateTime createdAt;

    public PostResponseDto(Long id, String title, String author, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.contents = contents;
        this.createdAt = createdAt;
    }

}
