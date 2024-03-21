package com.github.odyn666.appstudnet.entity;

import com.github.odyn666.appstudnet.entity.enums.Status;
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
@Table(name = "trainers")
public class TrainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Status status;

    @Column(name = "students_pass_rate")
    private Integer studentsPassRate;

    @OneToOne
    @JoinColumn(name = "car_id")
    private CarEntity carId;

    @OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchedulesTrainerEntity> trainTrainerSchedules;

    @OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LessonEntity> lessons;

    @OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerOpinions> trainerOpinions;
}

//TODO migrate entity to AppTrainer