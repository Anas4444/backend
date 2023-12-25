package com.jcibardo.blog.blog.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "daterange")
public class Daterange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "daterange_id")
    private Long daterange_id;

    @Column(name = "date_r")
    private Date date_r;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="journey_id")
    private Journey journey;
}
