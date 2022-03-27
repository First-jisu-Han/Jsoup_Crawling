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
public class NewsService {

    private final NewsRepository newsRepository; // @RequiredArgsConstructor로 의존성 주입까지

    @Transactional
    public void saveNews(News news) throws IOException {
        newsRepository.save(news);
        }

    public List<News> getNews(){
        return newsRepository.giveContents();
    }
}
