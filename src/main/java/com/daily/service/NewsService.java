package com.daily.service;

import com.daily.domain.News;
import com.daily.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService  {

    private final NewsRepository newsRepository; // @RequiredArgsConstructor로 의존성 주입까지

    @Transactional
    // 크롤링해서 저장하는 코드
    public void saveNews() throws IOException {
        String MainURL = "https://news.naver.com/main/ranking/popularDay.naver";
        Document doc = Jsoup.connect(MainURL).get();
        String[] urls = new String[5];

        for (int i = 1; i <= 5; i++) {
            urls[i - 1] = doc.select("#wrap > div.rankingnews._popularWelBase._persist > div.rankingnews_box_wrap._popularRanking > div > div:nth-child(" + i + ")> ul > li:nth-child(1) > div > a").attr("href");
        }

        Document[] doc1 = new Document[5];  // Url로 접근하는 문서들 배열

        for (int i = 0; i < doc1.length; i++) {
            doc1[i] = Jsoup.connect(urls[i]).get(); // Url 첫번쨰 접근
        }
        Elements[] etitles = new Elements[5];
        String[] titles = new String[5];  // 뉴스 기사
        String[] contents = new String[5]; //뉴스 내용
        for (int i = 0; i < etitles.length; i++) {
            etitles[i] = doc1[i].select(".media_end_head_headline");
            contents[i] = doc1[i].select("#newsct_article").text();  // 뉴스 내용 크롤링
            titles[i] = etitles[i].text();  // 뉴스 기사 제목 크롤링
        }
        for (int i = 0; i < titles.length; i++) {
            News newss = new News();
            newss.setContent(contents[i]);
            newss.setTitle(titles[i]);
            newsRepository.save(newss);
        }
    }

    public List<News> getNews() {
        return newsRepository.giveContents();
    }
}
