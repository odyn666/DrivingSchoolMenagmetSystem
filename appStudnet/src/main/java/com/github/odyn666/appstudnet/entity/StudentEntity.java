package com.github.odyn666.appstudnet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Status status;
    private Integer hoursLeft;
    private Date lastLogin;
    private Integer hoursDriven;
    @OneToMany(mappedBy = "student_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams;
    //TODO last to variables

}
