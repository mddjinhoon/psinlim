package org.mddjinhoon.demo.other.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckController {

    @RequestMapping("/jsp/hello")
    public String hello() {
        return "";
    }
}