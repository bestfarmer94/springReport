package com.sparta.springreport.dto;

import lombok.Getter;

@Getter
public class PostDictionary {
    // 다중 객체 받는 실험을 해보았다. 잘된다.
    private PostRequestDto postRequestDto;
    private String password;
}
