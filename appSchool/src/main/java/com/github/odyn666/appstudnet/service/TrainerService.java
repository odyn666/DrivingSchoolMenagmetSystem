package com.github.odyn666.appstudnet.service;

import com.github.odyn666.appstudnet.dto.TrainerEntityDto;
import com.github.odyn666.appstudnet.entity.TrainerEntity;
import com.github.odyn666.appstudnet.mapper.TrainerMapper;
import com.github.odyn666.appstudnet.repository.TrainerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerEntityRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    public List<TrainerEntityDto> getTrainers() {
        List<TrainerEntity> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainerMapper::toDto)
                .toList();
    }
}
