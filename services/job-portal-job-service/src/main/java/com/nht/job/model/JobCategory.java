package com.nht.job.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="job_categories")
@Getter
public class JobCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column( unique = true)
    private String slug;


    private String description;

    private String iconUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobCategory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobCategory> subCategories = new ArrayList<>();
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private Boolean active = true;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
