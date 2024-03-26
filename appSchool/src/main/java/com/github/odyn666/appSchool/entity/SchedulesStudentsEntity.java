package com.github.odyn666.appSchool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SCHEDULES_STUDENTS")
public class SchedulesStudentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private StudentEntity student;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "STARTING_HOUR")
    private Timestamp startingHour;

    @Column(name = "ENDING_HOUR")
    private Timestamp endingHour;

}
