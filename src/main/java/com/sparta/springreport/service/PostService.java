package com.sparta.springreport.service;

import com.sparta.springreport.dto.PostDictionary;
import com.sparta.springreport.dto.PostPassword;
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
            result.add(new PostResponseDto().entityToDto(post));
        }

        return result;
    }

    @Transactional
    public PostResponseDto makePost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        for(int i=0; i<1000; i++){
            post = new Post(postRequestDto);
            postRepository.save(post);
        }
        return new PostResponseDto().entityToDto(post);
    }

    @Transactional
    public PostResponseDto findOne(Long id) {
        return new PostResponseDto().entityToDto(findById(id));
    }

    @Transactional
    public PostResponseDto update(Long id, PostDictionary postDictionary) {
        Post post = findById(id);

        if(confirm_password(postDictionary.getPassword(), post)){
            post.update(postDictionary.getPostRequestDto());
        }

        return new PostResponseDto().entityToDto(post);
    }

    @Transactional
    public boolean delete(Long id, PostPassword postPassword) {
        Post post = findById(id);
        boolean success = confirm_password(postPassword.getPassword(), post);
        if(success){
            postRepository.deleteById(id);
        }
        return success;
    }

    // 캡슐화 관점에서 DTO 안으로 옮겼다.
//    public PostResponseDto entityToDto(Post post){
//        return new PostResponseDto(post.getId(), , , , , )
//    }

    public Post findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return post;
    }

    public boolean confirm_password(String password, Post post){
        return password.equals(post.getPassword());
    }
}
