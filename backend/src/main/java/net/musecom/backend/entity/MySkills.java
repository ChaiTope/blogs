package net.musecom.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="myskills")
@Setter
@Getter
public class MySkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int value;

}
