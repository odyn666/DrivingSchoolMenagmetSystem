package com.github.odyn666.appstudnet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @ManyToOne
    private StudentEntity student_id;
    @Column(name = "is_passed", nullable = false)
    private Boolean isPassed;

    @Column(name = "score", nullable = false)
    private Short score;

    @Column(name = "description", length = 255)
    private String description;


}
