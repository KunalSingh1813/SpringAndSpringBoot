package com.example.SpringSecurity.Demo.utils;

import com.example.SpringSecurity.Demo.dto.PostDTO;
import com.example.SpringSecurity.Demo.entities.PostEntity;
import com.example.SpringSecurity.Demo.entities.User;
import com.example.SpringSecurity.Demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;
    public boolean isOwnerOfPost(Long postId){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDTO post = postService.getPostById(postId);
        return post.getAuthor().getId().equals(user.getId());
    }
}
