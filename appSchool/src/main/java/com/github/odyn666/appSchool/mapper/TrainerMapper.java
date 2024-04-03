package com.github.odyn666.appSchool.mapper;

import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    public TrainerEntityDto toDto(TrainerEntity entity) {

        Integer iPassRate = entity.getStudentsPassRate();
        Float fPassRate = Float.parseFloat(String.valueOf((iPassRate/10.0)));

        return new TrainerEntityDto(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getIdentifier(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                fPassRate,
                entity.getTrainerOpinions()
        );
    }

    public TrainerEntity toEntity(TrainerEntityDto dto) {

        float fPassRate = dto.getStudentsPassRate()*10;
        Integer iPassRate = Integer.parseInt(Float.toString(fPassRate));

        TrainerEntity entity = new TrainerEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setIdentifier(dto.getIdentifier());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setStudentsPassRate(iPassRate);
        entity.setTrainerOpinions(dto.getTrainerOpinions());
        return entity;
    }
}
