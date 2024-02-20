package com.mytripdiary.repository;

import com.mytripdiary.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryPostRepository {
    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    //저장
    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }

    //아이디별 조회
    public Post findById(Long id) {
        return store.get(id);
    }

    //모든 포스트 조회
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    //포스트 수정
    public void update(Long id, Post updateParam) {
        Post findPost = findById(id);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
        findPost.setImageFiles(updateParam.getImageFiles());
        findPost.setAttachFile(updateParam.getAttachFile());
    }

    //삭제
    public void delete(Long id) {
        store.remove(id);
    }

    //저장소 초기화
    public void clearStore() {
        store.clear();
    }
}
