package org.mddjinhoon.demo.other.web;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PostsController {

    @GetMapping("/post/report")
    public String report() {
        return "/post/report";
    }
}
