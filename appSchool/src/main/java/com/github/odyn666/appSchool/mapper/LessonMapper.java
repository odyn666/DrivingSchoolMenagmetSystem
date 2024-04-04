package com.github.odyn666.appSchool.mapper;

import com.github.odyn666.appSchool.dto.LessonEntityDto;
import com.github.odyn666.appSchool.entity.LessonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonMapper {

    private final StudentMapper studentMapper;
    private final TrainerMapper trainerMapper;

    public LessonEntityDto toDto(LessonEntity entity) {
        LessonEntityDto dto = new LessonEntityDto();
        dto.setId(entity.getId());
        dto.setStudent(studentMapper.toDto(entity.getStudent()));
        dto.setTrainer(trainerMapper.toDto(entity.getTrainer()));
        dto.setDate(entity.getDate());
        dto.setStartingHour(entity.getStartingHour());
        dto.setEndingHour(entity.getEndingHour());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
