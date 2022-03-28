package com.daily.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class News {

    @Id @GeneratedValue
    @Column(name="news_id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

}
