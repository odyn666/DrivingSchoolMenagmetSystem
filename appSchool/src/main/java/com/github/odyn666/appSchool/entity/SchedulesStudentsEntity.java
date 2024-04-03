package com.github.odyn666.appSchool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @JsonBackReference
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private StudentEntity student;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "STARTING_HOUR")
    private String startingHour;

    @Column(name = "ENDING_HOUR")
    private String endingHour;

}
