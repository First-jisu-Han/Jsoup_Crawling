package com.daily.controller;

import com.daily.domain.News;
import com.daily.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;  // 의존성 주입


    @ResponseBody
    @GetMapping("/news")
    public void go(HttpServletResponse response) throws IOException {
        List<News> news =newsService.getNews();
        newsService.saveNews();
        for(News ne:news){
            ne.getTitle();
            ne.getContent();
        }
    }

}
