package com.github.odyn666.appSchool.service;

import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.entity.enums.Status;
import com.github.odyn666.appSchool.exception.exceptions.TrainerNotFoundException;
import com.github.odyn666.appSchool.mapper.TrainerMapper;
import com.github.odyn666.appSchool.repository.TrainerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerEntityRepository trainerRepository;
    private final TrainerMapper trainerMapper;


    //*CREATE
    public TrainerEntity saveTrainer(TrainerEntity dto) {
        return trainerRepository.save(dto);
    }

    //*READ
    public List<TrainerEntityDto> getTrainers() {
        List<TrainerEntity> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainerMapper::toDto)
                .toList();
    }

    public TrainerEntity getTrainerByID(Long id){
        return trainerRepository.findTrainerEntityById(id).orElse(null);
    }

    public TrainerEntityDto getTrainerByStatus(Status status){
        TrainerEntity trainerEntity = trainerRepository.findTrainerEntityByStatus(status).orElseThrow(TrainerNotFoundException::new);
        return trainerMapper.toDto(trainerEntity);
    }

    List<TrainerEntityDto> getTrainersByStudentsPassRate(){
        return trainerRepository.findTrainerEntityByStudentsPassRate()
                .stream()
                .map(trainerMapper::toDto)
                .toList();
    }

    public TrainerEntityDto getTrainerByIdentifier(String identifier){
        TrainerEntity trainerEntity = trainerRepository.findTrainerEntityByIdentifier(identifier).orElseThrow(TrainerNotFoundException::new);
        return trainerMapper.toDto(trainerEntity);
    }

    public List<LessonEntity> findAllTrainerLessonsByTrainerID(Long id){
        return trainerRepository.findAllLessonsForTrainer(id).orElseThrow(TrainerNotFoundException::new);
    }

    //*UPDATE


    //*DELETE
}
