package com.github.odyn666.apptrainer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "TRAINERS")
public class TrainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "STUDENTS_PASS_RATE")
    private Integer studentsPassRate;

    @OneToOne(mappedBy = "trainer")
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private CarEntity carId;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SchedulesTrainerEntity> trainTrainerSchedules;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<LessonEntity> lessons;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TrainerOpinions> trainerOpinions;
}

//TODO migrate entity to AppTrainer