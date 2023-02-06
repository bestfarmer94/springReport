package com.sparta.springreport.controller;

import com.sparta.springreport.dto.PostRequestDto;
import com.sparta.springreport.dto.PostResponseDto;
import com.sparta.springreport.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public List<PostResponseDto> getPostList() {
        return postService.getPostList();
    }

    @PostMapping("/api/post")
    public PostResponseDto makePost(@RequestBody PostRequestDto postRequestDto){
        return postService.makePost(postRequestDto);
    }

    @GetMapping("/api/post/{id}")
    public PostResponseDto details(@PathVariable Long id){
        return postService.findOne(id);
    }

    @PutMapping("/api/post/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.update(id, postRequestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public String delete(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return "{success : " + postService.delete(id, postRequestDto) + "}";
    }
}
