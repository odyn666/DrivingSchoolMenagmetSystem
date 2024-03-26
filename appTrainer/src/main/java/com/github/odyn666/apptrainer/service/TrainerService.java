package com.github.odyn666.apptrainer.service;

import com.github.odyn666.apptrainer.dto.TrainerEntityDto;
import com.github.odyn666.apptrainer.entity.TrainerEntity;
import com.github.odyn666.apptrainer.mapper.TrainerMapper;
import com.github.odyn666.apptrainer.repository.TrainerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerEntityRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    private List<TrainerEntityDto> getTrainers() {
        List<TrainerEntity> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainerMapper::toDto)
                .toList();
    }
}
