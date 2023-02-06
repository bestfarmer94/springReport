package com.sparta.springreport.controller;

import com.sparta.springreport.dto.PostRequestDto;
import com.sparta.springreport.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @GetMapping("/api/posts")
    public List<PostRequestDto> getPostList() {
        return postService.getPostList();
    }

    @PostMapping("/api/post")
    public String makePost(@RequestBody PostRequestDto postRequestDto){
        postService.makePost(postRequestDto);
        return "완료";
    }
}
