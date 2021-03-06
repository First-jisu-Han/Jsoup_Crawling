package com.daily.repository;

import com.daily.domain.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor // final 붙은 생성자를 자동생성
public class NewsRepository {

    private final EntityManager em;

    public void save(News news) {
            em.persist(news);
    }
    public List<News> giveContents(){
        return em.createQuery("select n from News n",News.class).getResultList();
    }





}
