package com.mytripdiary.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private Long id;
    private String title;
    private String content;

    public Post() {
    }

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
