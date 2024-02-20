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
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;

    public Post() {
    }

    public Post(Long id, String title, String content, UploadFile attachFile, List<UploadFile> imageFiles) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }
}
