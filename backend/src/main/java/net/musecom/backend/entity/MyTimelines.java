package net.musecom.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="mytimelines")
@Setter
@Getter
public class MyTimelines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobtitle;
    private String wheres;
    private String wdate;
}
