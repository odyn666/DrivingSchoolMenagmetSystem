package com.github.odyn666.appstudnet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String identifier;
    private String phoneNumber;
    private String email;
    private String password;
    private String status;
    private Integer studentsPassRate;
    private Integer carId;
    @OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchedulesTrainerEntity> trainTrainerSchedules;
    @OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lessons> lessons;
    @OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerOpinions> trainerOpinions;
}

//TODO migrate entity to AppTrainer