package org.mddjinhoon.demo.base1.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board/index")
    public String index() {
        return "/board/index";
    }
}
