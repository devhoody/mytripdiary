package com.mytripdiary.repository;

import com.mytripdiary.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PostRepositoryTest {
    MemoryPostRepository postRepository = new MemoryPostRepository();

    @AfterEach
    void afterEach() {
        postRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Post post = new Post(1L, "제목", "내용", null);

        //when
        Post savePost = postRepository.save(post);

        //then
        assertThat(post.getTitle()).isEqualTo(savePost.getTitle());
    }

    @Test
    void findById() {
        //given
        Post post = new Post(1L, "제목", "내용", null);
        Post savedPost = postRepository.save(post);

        //when
        Post findPost = postRepository.findById(savedPost.getId());

        //then
        assertThat(post.getId()).isEqualTo(findPost.getId());
    }

    @Test
    void findAll(){
        //given
        Post post1 = new Post(1L, "제목", "내용", null);
        Post post2 = new Post(2L, "제목2", "내용2", null);
        postRepository.save(post1);
        postRepository.save(post2);

        //when
        List<Post> list = postRepository.findAll();
        //then
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void update(){
        //given
        Post post1 = new Post(1L, "제목", "내용", null);
        postRepository.save(post1);
        post1.setTitle("제목3");

        //when
        postRepository.update(post1.getId(), post1);
        Post findPost = postRepository.findById(post1.getId());

        //then
        assertThat(findPost.getTitle()).isEqualTo("제목3");
    }

    @Test
    void delete(){
        //given
        Post post1 = new Post(1L, "제목", "내용", null);
        Post post2 = new Post(2L, "제목2", "내용2", null);
        postRepository.save(post1);
        postRepository.save(post2);

        //when
        postRepository.delete(1L);
        //then
        assertThat(postRepository.findById(1L)).isNull();
    }
}
