package com.mytripdiary.controller;

import com.mytripdiary.domain.Post;
import com.mytripdiary.service.PostServiceImp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("add")
    public String add(Model model){
        Post post = new Post();
        model.addAttribute("post", post);
        return "/post/addForm";
    }

    @PostMapping("add")
    public String add(@ModelAttribute Post post, Model model, RedirectAttributes redirectAttributes){
        Post savedPost = postService.save(post);
        model.addAttribute("post", savedPost);

        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/posts/{postId}";
    }

    @GetMapping("{postId}")
    public String post(@PathVariable(name = "postId") Long id, Model model){
        Post findedPost = postService.findById(id);
        model.addAttribute("post", findedPost);

        return "/post/post";
    }

    @GetMapping("{postId}/edit")
    public String edit(@PathVariable(name = "postId") Long id, Model model){
        Post findedPost = postService.findById(id);
        model.addAttribute("post", findedPost);

        return "/post/editForm";
    }

    @PostMapping("{postId}/edit")
    public String edit(@ModelAttribute Post post, Model model, RedirectAttributes redirectAttributes){
        postService.modify(post.getId(), post);
        model.addAttribute("post", post);

        redirectAttributes.addAttribute("postId", post.getId());

        return "redirect:/posts/{postId}";
    }

    @GetMapping("{postId}/delete")
    public String delete(@PathVariable(name = "postId") Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }


}
