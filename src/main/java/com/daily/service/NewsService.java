package com.daily.service;

import com.daily.domain.News;
import com.daily.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {

    private final NewsRepository newsRepository; // @RequiredArgsConstructor로 의존성 주입까지

    @Transactional
    public void saveNews(News news){



        newsRepository.save(news);
    }

    public List<News> giveContents(News news){
        return newsRepository.giveContents();
    }

}
