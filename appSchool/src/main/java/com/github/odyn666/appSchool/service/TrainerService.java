package com.github.odyn666.appSchool.service;

import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.dto.auth.TrainerRegistrationDto;
import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.entity.enums.Status;
import com.github.odyn666.appSchool.exception.exceptions.TrainerNotFoundException;
import com.github.odyn666.appSchool.mapper.TrainerMapper;
import com.github.odyn666.appSchool.repository.TrainerEntityRepository;
import com.github.odyn666.appSchool.utils.PasswordHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerEntityRepository trainerRepository;
    private final TrainerMapper trainerMapper;
    private final PasswordHasher passwordHasher;


    //*CREATE
    @Transactional
    public TrainerEntity saveTrainer(TrainerEntity entity) {
        entity.setPassword(passwordHasher.hashPassword(entity.getPassword()));
        return trainerRepository.save(entity);
    }

    @Transactional
    public TrainerEntity saveTrainer(TrainerRegistrationDto dto) {
        TrainerEntity trainer = new TrainerEntity();
        trainer.setFirstName(dto.getFirstName());
        trainer.setLastName(dto.getLastName());
        trainer.setPhoneNumber(dto.getPhoneNumber());
        trainer.setEmail(dto.getEmail());

        return trainerRepository.save(trainer);
    }

    //*READ
    public List<TrainerEntityDto> getTrainers() {
        List<TrainerEntity> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainerMapper::toDto)
                .toList();
    }

    public List<TrainerEntity>getAllTrainerEntities(){
        return trainerRepository.findAll();
    }

    public TrainerEntity getTrainerByID(Long id) {
        return trainerRepository.findTrainerEntityById(id).orElseThrow(TrainerNotFoundException::new);
    }

    public List<TrainerEntityDto> getTrainerByStatus(Status status) {
        List<TrainerEntity> trainerEntities = trainerRepository.findAllByStatus(status);
        return trainerEntities.stream()
                .map(trainerMapper::toDto)
                .toList();
    }

    public List<TrainerEntityDto> getTrainersByStudentsPassRate() {
        return trainerRepository.findTrainerEntityByStudentsPassRate()
                .stream()
                .map(trainerMapper::toDto)
                .toList();
    }

    public TrainerEntityDto getTrainerByIdentifier(String identifier) {
        TrainerEntity trainerEntity = trainerRepository.findTrainerEntityByIdentifier(identifier).orElseThrow(TrainerNotFoundException::new);
        return trainerMapper.toDto(trainerEntity);
    }

    public List<LessonEntity> getAllTrainerLessonsByTrainerID(Long id) {
        return trainerRepository.findAllLessonsForTrainer(id).orElseThrow(TrainerNotFoundException::new);
    }
    public TrainerEntity getTrainerByEmail(String email) {
        return trainerRepository.findTrainerEntityByEmail(email).orElseThrow(TrainerNotFoundException::new);
    }

    @Transactional
    public TrainerEntity updateTrainer(Long id, Map<String, Object> variables) {
        TrainerEntity trainer = getTrainerByID(id);

        variables.forEach((key, value) -> {
            if (key.equals("id")) return;

            Field field = ReflectionUtils.findField(TrainerEntity.class, key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, trainer, value);
        });
        return trainer;
    }

    public Boolean trainerEmailExists(String email) {
        return trainerRepository.existsTrainerEntityByEmail(email);
    }


    //*UPDATE


    //*DELETE
}
