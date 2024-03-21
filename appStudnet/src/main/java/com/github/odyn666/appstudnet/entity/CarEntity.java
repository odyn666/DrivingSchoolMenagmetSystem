package com.github.odyn666.appstudnet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CARS")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PLATES")
    private String plates;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PROD_YEAR")
    private Integer prodYear;

    @Column(name = "MILAGE")
    private Integer mileage;

    @Column(name = "LAST_MAINTENANCE_MILEAGE")
    private Integer lastMaintenanceMileage;

    @OneToOne
    private TrainerEntity trainer;
}
