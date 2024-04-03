package com.github.odyn666.appSchool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.odyn666.appSchool.entity.enums.ExamType;
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
@Table(name = "EXAMS")
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXAM_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "STUDENT_ID", nullable = false, referencedColumnName = "ID")
    private StudentEntity student;

    @Column(name = "IS_PASSED", nullable = false)
    private Boolean isPassed;

    @Column(name = "SCORE", nullable = false)
    private Short score;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;
}
