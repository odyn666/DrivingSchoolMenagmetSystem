package com.github.odyn666.appstudnet.entity;

import com.github.odyn666.appstudnet.entity.enums.Status;
import jakarta.persistence.*;
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
    private Status status;

    @Column(name = "HOURS_LEFT", nullable = false)
    private Integer hoursLeft;

    @Column(name = "LAST_LOGIN", nullable = false)
    private Date lastLogin;

    @Column(name = "HOURSDRIVEN", nullable = false)
    private Integer hoursDriven;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamEntity> exams;

    @Column(name = "lessons_attended")
    private Short lessonsAttended;

    @Column(name = "lessons_omitted")
    private Short lessonsOmitted;

    @Column(name = "is_finial_exam_passed", nullable = false)
    private Boolean isFinialExamPassed;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchedulesStudentsEntity> schedulesID;
}



