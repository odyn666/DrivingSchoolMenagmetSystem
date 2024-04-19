package com.github.odyn666.appauth.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TrainerOpinionsDTO implements Serializable {
    private Long trainerID;
    private Long studentID;
    private String description;


}
