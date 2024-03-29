package com.mytripdiary.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Post {
    private Long id;
    private String title;
    private String content;
    private List<UploadFile> imageFiles;

    public Post() {
    }

    public Post(Long id, String title, String content, List<UploadFile> imageFiles) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageFiles = imageFiles;
    }
}
