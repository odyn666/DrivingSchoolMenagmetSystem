package com.github.odyn666.appSchool.service;

import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.entity.TrainerEntity;
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




    //*UPDATE


    //*DELETE
}
