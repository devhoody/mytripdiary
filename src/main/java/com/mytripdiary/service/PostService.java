package com.mytripdiary.service;

import com.mytripdiary.domain.Post;

import java.util.List;

public interface PostService {
    Post save(Post post);

    void modify(Long postId, Post post);

    void delete(Long postId);

    List<Post> findAll();

    Post findById(Long postId);

}
