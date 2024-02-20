package com.mytripdiary.controller;

import com.mytripdiary.domain.Post;
import com.mytripdiary.domain.PostForm;
import com.mytripdiary.domain.UploadFile;
import com.mytripdiary.file.FileStore;
import com.mytripdiary.service.PostServiceImp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class PostController {
    private PostServiceImp postService;
    private FileStore fileStore;

    @GetMapping("posts")
    public String posts(Model model) {
        List<Post> list = postService.findAll();
        model.addAttribute("posts", list);
        return "/post/posts";
    }

    @GetMapping("posts/add")
    public String add(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "/post/addForm";
    }

    @PostMapping("posts/add")
    public String add(@ModelAttribute PostForm form, Model model, RedirectAttributes redirectAttributes) throws IOException {

        MultipartFile attachFile = form.getAttachFile();
        List<MultipartFile> imageFiles = form.getImageFiles();

        UploadFile uploadFile = fileStore.fileStore(attachFile);
        List<UploadFile> uploadFiles = fileStore.filesStore(imageFiles);

        log.info("uploadFilename : " + uploadFile.getUploadFilename());
        log.info("imageFiles : " + uploadFiles.get(0).toString());

        //데이터베이스 저장
        Post post = Post.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .attachFile(uploadFile)
                .imageFiles(uploadFiles)
                .build();

        Post savedPost = postService.save(post);
        model.addAttribute("post", savedPost);

        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/posts/{postId}";
    }

    @ResponseBody
    @GetMapping("images/{filename}")
    public Resource image(@PathVariable(name = "filename") String filename) throws MalformedURLException {
        String fullPath = fileStore.getFullPath(filename);
        return new UrlResource("file:" + fullPath);
    }

    @GetMapping("posts/{postId}")
    public String post(@PathVariable(name = "postId") Long id, Model model) {
        Post findedPost = postService.findById(id);
        model.addAttribute("post", findedPost);

        return "/post/post";
    }

    @GetMapping("posts/{postId}/edit")
    public String edit(@PathVariable(name = "postId") Long id, Model model) {
        Post findedPost = postService.findById(id);
        model.addAttribute("post", findedPost);

        return "/post/editForm";
    }

    @PostMapping("posts/{postId}/edit")
    public String edit(@ModelAttribute Post post, Model model, RedirectAttributes redirectAttributes) {
        postService.modify(post.getId(), post);
        model.addAttribute("post", post);

        redirectAttributes.addAttribute("postId", post.getId());

        return "redirect:/posts/{postId}";
    }

    @GetMapping("posts/{postId}/delete")
    public String delete(@PathVariable(name = "postId") Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }


}
