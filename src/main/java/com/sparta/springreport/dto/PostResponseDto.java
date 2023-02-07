package com.sparta.springreport.dto;

import com.sparta.springreport.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private LocalDateTime createdAt;

    // AllArgs
//    public PostResponseDto(Long id, String title, String author, String contents, LocalDateTime createdAt) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.contents = contents;
//        this.createdAt = createdAt;
//    }

    public static PostResponseDto entityToDto(Post post){
        PostResponseDto responseDto = new PostResponseDto(
                post.getId(),
            post.getTitle(),
            post.getAuthor(),
            post.getContents(),
            post.getCreatedAt());
        return responseDto;
    }
}
