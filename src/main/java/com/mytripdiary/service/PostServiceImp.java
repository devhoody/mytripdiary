package com.mytripdiary.service;

import com.mytripdiary.domain.Post;
import com.mytripdiary.repository.MemoryPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    MemoryPostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void modify(Long postId, Post post) {
        postRepository.update(postId, post);
    }

    @Override
    public void delete(Long postId) {
        postRepository.delete(postId);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId);
    }
}
