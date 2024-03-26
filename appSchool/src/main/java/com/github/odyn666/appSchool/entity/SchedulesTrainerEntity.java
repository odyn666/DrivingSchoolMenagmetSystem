package com.github.odyn666.appSchool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "SCHEDULES_TRAINER")
public class SchedulesTrainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "TRAINER_ID", referencedColumnName = "ID")
    private TrainerEntity trainer;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "STARTING_HOUR")
    private String startingHour;

    @Column(name = "ENDING_HOUR")
    private String endingHour;
}
