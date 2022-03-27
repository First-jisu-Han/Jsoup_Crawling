package com.daily.controller;

import com.daily.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;  // 의존성 주입

    @GetMapping("/news")
    public String list(Model model){
        model.addAttribute()
    }
    
}
