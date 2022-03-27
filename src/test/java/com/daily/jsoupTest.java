package com.daily;

import org.jboss.jandex.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class jsoupTest {
    public static void main(String[] args) throws IOException {
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
    }}