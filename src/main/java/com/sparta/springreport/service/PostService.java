package com.sparta.springreport.service;

import com.sparta.springreport.dto.PostRequestDto;
import com.sparta.springreport.dto.PostResponseDto;
import com.sparta.springreport.entity.Post;
import com.sparta.springreport.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostList() {
        List<Post> list = postRepository.findAllByOrderByCreatedAtDesc();

        List<PostResponseDto> result = new ArrayList<>();
        for (Post post : list) {
            result.add(entityToDto(post));
        }

        return result;
    }

    @Transactional
    public PostResponseDto makePost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return entityToDto(post);
    }

    @Transactional
    public PostResponseDto findOne(Long id) {
        return entityToDto(findById(id));
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto postRequestDto) {
        Post post = findById(id);

        if(confirm_password(postRequestDto, post)){
            post.update(postRequestDto);
        }

        return entityToDto(post);
    }

    @Transactional
    public boolean delete(Long id, PostRequestDto postRequestDto) {
        Post post = findById(id);
        boolean success = confirm_password(postRequestDto, post);
        if(success){
            postRepository.deleteById(id);
        }
        return success;
    }

    public PostResponseDto entityToDto(Post post){
        return new PostResponseDto(post.getId(), post.getTitle(), post.getAuthor(), post.getContents(), post.getCreatedAt());
    }

    public Post findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return post;
    }

    public boolean confirm_password(PostRequestDto postRequestDto, Post post){
        return postRequestDto.getPassword().equals(post.getPassword());
    }
}
