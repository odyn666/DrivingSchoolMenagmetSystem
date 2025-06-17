package com.github.odyn666.appSchool.entity;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="archive")
@Getter
@Setter
public class ArchiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "delete_threshold")
    private Integer deleteThresholdInMonths;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "archive_id") // join column added to student table
    private List<StudentEntity> pastStudents = new ArrayList<>();

}
