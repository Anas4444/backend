package com.jcibardo.blog.blog.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long item_id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "img")
    private String img;
}
