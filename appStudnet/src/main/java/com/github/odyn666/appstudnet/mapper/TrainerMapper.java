package com.github.odyn666.appstudnet.mapper;

import com.github.odyn666.appstudnet.dto.TrainerEntityDto;
import com.github.odyn666.appstudnet.entity.TrainerEntity;
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
}
