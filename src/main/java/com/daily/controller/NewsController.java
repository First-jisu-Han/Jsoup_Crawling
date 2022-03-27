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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;  // 의존성 주입

    @GetMapping("/news")
    @ResponseBody
    public void go(HttpServletResponse response) throws IOException {

        String MainURL = "https://news.naver.com/main/ranking/popularDay.naver";
        Document doc = Jsoup.connect(MainURL).get();
        String[] urls = new String[5];
        for (int i = 1; i <=5; i++) {
            urls[i-1]= doc.select("#wrap > div.rankingnews._popularWelBase._persist > div.rankingnews_box_wrap._popularRanking > div > div:nth-child("+i+")> ul > li:nth-child(1) > div > a").attr("href");
        }
        Document[] doc1 = new Document[5];  // Url로 접근하는 문서들 배열
        for (int i = 0; i < doc1.length; i++) {
            doc1[i] = Jsoup.connect(urls[i]).get(); // Url 첫번쨰 접근
        }
        Elements[] etitles = new Elements[5];
        String[] titles = new String[5];  // 뉴스 기사
        String[] contents=new String[5]; //뉴스 내용
        for (int i = 0; i < etitles.length; i++) {
            etitles[i] = doc1[i].select(".media_end_head_headline");
            contents[i]=doc1[i].select("#newsct_article").text();  // 뉴스 내용 크롤링
            titles[i]=etitles[i].text();  // 뉴스 기사 제목 크롤링
        }
        for(int i=0;i<titles.length;i++){
            News news=new News();
            news.setContent(contents[i]);
            news.setTitle(titles[i]);
            newsService.saveNews(news);
        }

    }

}
