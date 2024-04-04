package com.github.odyn666.appSchool.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.odyn666.appSchool.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "STUDENTS")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PKK_NUMBER", nullable = false, unique = true)
    private String pkkNumber;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "HOURS_LEFT", nullable = false)
    private Integer hoursLeft;

    @Column(name = "LAST_LOGIN", nullable = false)
    private Date lastLogin;

    @Column(name = "HOURS_DRIVEN", nullable = false)
    @DefaultValue("0")
    private Integer hoursDriven;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ExamEntity> exams;

    @Column(name = "LESSONS_ATTENDED")
    @DefaultValue("0")
    private Short lessonsAttended;

    @Column(name = "LESSONS_OMITTED")
    @DefaultValue("0")
    private Short lessonsOmitted;

    @Column(name = "IS_FINAL_EXAM_PASSED", nullable = false)
    private Boolean isFinialExamPassed;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SchedulesStudentsEntity> schedulesID;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<LessonEntity> lessons;
}
