package com.github.odyn666.appstudnet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "trainer_opinions")
public class TrainerOpinions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private TrainerEntity trainer;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;

    @Column(name = "OPINION")
    private String opinion;

}
