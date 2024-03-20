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
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable=false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "hours_left", nullable = false)
    private Integer hoursLeft;

    @Column(name = "last_login", nullable = false)
    private Date lastLogin;

    @Column(name = "hoursDriven", nullable = false)
    private Integer hoursDriven;

    @OneToMany(mappedBy = "student_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "exams", nullable = false)
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
