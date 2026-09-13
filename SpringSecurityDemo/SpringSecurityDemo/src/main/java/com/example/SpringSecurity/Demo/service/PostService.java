package com.example.SpringSecurity.Demo.service;

import com.example.SpringSecurity.Demo.dto.PostDTO;


import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
