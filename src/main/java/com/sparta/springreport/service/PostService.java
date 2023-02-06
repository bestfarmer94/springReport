package com.sparta.springreport.service;

import com.sparta.springreport.dto.PostRequestDto;
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
    public List<PostRequestDto> getPostList() {
        List<Post> list = postRepository.findAllByOrderByCreatedAtDesc();

        List<PostRequestDto> result = new ArrayList<>();
        for (Post post : list) {
            result.add(new PostRequestDto(post.getId(), post.getTitle(), post.getAuthor(), post.getContents(), post.getCreatedAt()));
        }

        return result;
    }

    public void makePost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
    }
}
