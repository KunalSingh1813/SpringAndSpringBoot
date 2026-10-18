package com.example.SpringSecurity.Demo.controllers;


import com.example.SpringSecurity.Demo.dto.PostDTO;
import com.example.SpringSecurity.Demo.entities.PostEntity;
import com.example.SpringSecurity.Demo.entities.User;
import com.example.SpringSecurity.Demo.exceptions.ResourceNotFoundException;
import com.example.SpringSecurity.Demo.repositories.PostRepository;
import com.example.SpringSecurity.Demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    //@PreAuthorize("hasAnyRole('USER','ADMIN') OR hasAuthorities('POST_VIEW')")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDTO getPostById(@PathVariable Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id "+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

}
