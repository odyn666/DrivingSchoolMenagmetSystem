package com.github.odyn666.appSchool.mapper;

import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    public TrainerEntityDto toDto(TrainerEntity entity) {

        return new TrainerEntityDto(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getIdentifier(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getStudentsPassRate(),
                entity.getTrainerOpinions()
        );
    }

    public TrainerEntity toEntity(TrainerEntityDto dto) {
        TrainerEntity entity = new TrainerEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setIdentifier(dto.getIdentifier());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setStudentsPassRate(dto.getStudentsPassRate());
        entity.setTrainerOpinions(dto.getTrainerOpinions());
        return entity;
    }
}
