package org.mddjinhoon.demo.other.web;

import lombok.RequiredArgsConstructor;
import org.mddjinhoon.demo.other.service.posts.PostsService;
import org.mddjinhoon.demo.other.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/test")
    public String test() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@");

        return "test";
    }

    @RequestMapping("/jsp/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}