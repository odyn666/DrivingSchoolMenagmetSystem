package com.github.odyn666.appstudnet.entity;

import com.github.odyn666.appstudnet.entity.enums.Status;
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
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TRAINER_ID", nullable = false)
    @ManyToOne
    private TrainerEntity trainer;
    @OneToOne
    private StudentEntity student;
    private Date date;
    private String startingHour;
    private String endingHour;
    private Status status;
}
