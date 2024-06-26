package com.github.odyn666.appSchool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.odyn666.appSchool.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "LESSONS")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "TRAINER_ID", referencedColumnName = "id", nullable = false)
    private TrainerEntity trainer;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private StudentEntity student;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "STARTING_HOUR")
    private String startingHour;

    @Column(name = "ENDING_HOUR")
    private String endingHour;

    @Column(name = "STATUS")
    private Status status;
}
