package net.musecom.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DynamicInsert
@Table(name="post_table")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String post;

    private String category;
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name="create_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer hit;
    
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer empathy;
    
    private String hashtag;
    private Long ntime;

}
