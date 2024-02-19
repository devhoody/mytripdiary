package com.mytripdiary.controller;

import com.mytripdiary.domain.Post;
import com.mytripdiary.service.PostServiceImp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private PostServiceImp postService;

    @GetMapping("")
    public String posts(Model model){
        List<Post> list = postService.findAll();
        model.addAttribute("posts", list);
        return "/post/posts";
    }


}
