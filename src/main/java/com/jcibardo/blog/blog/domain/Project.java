package com.jcibardo.blog.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import java.io.Serializable;

@Entity
@Data
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Long project_id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "project")
    //@JsonIgnore
    private List<Journey> journeys;


}
