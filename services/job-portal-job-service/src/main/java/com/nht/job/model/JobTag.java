package com.nht.job.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="job_tags")
@Getter
public class JobTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
