package com.jcibardo.blog.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "journey")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "journey_id")
    private Long journey_id;

    @Column(name = "title")
    private String title;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "place")
    private String place;

    @Column(name = "written_date")
    private String written_date;

    @Lob
    @Column(name = "img")
    private String img;

    @OneToMany(mappedBy = "journey")
    //@JsonIgnore
    private List<Daterange> dates;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id")
    private Project project;
}
