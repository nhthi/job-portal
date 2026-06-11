package com.nht.job.model;

import com.nht.job.domain.ProficiencyLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resume_skills")
public class ResumeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Column(nullable = false)
    private String skillName;

    private ProficiencyLevel proficiencyLevel= ProficiencyLevel.BEGINNER;

    private Integer yearsOfExperience = 0;

    @Column(nullable = false)
    private Integer displayOrder=0;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;










}
