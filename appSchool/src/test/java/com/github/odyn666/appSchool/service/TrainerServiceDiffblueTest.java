package com.github.odyn666.appSchool.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.odyn666.appSchool.dto.TrainerEntityDto;
import com.github.odyn666.appSchool.dto.auth.TrainerRegistrationDto;
import com.github.odyn666.appSchool.entity.CarEntity;
import com.github.odyn666.appSchool.entity.LessonEntity;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.entity.enums.Status;
import com.github.odyn666.appSchool.exception.exceptions.TrainerNotFoundException;
import com.github.odyn666.appSchool.mapper.TrainerMapper;
import com.github.odyn666.appSchool.repository.TrainerEntityRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TrainerService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class TrainerServiceDiffblueTest {
    @MockBean
    private TrainerEntityRepository trainerEntityRepository;

    @MockBean
    private TrainerMapper trainerMapper;

    @Autowired
    private TrainerService trainerService;

    /**
     * Method under test: {@link TrainerService#saveTrainer(TrainerEntity)}
     */
    @Test
    void testSaveTrainer() {
        // Arrange
        TrainerEntity trainer = getTrainerEntity(new CarEntity());

        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(trainer);

        TrainerEntity trainer2 = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer2);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);
        when(trainerEntityRepository.save(Mockito.<TrainerEntity>any())).thenReturn(trainerEntity);

        CarEntity carId3 = new CarEntity();
        carId3.setBrand("Brand");
        carId3.setId(1L);
        carId3.setLastMaintenanceMileage(1);
        carId3.setMileage(1);
        carId3.setModel("Model");
        carId3.setPlates("Plates");
        carId3.setProdYear(1);
        carId3.setTrainer(new TrainerEntity());

        TrainerEntity trainer3 = getTrainerEntity(carId3);

        CarEntity carId4 = new CarEntity();
        carId4.setBrand("Brand");
        carId4.setId(1L);
        carId4.setLastMaintenanceMileage(1);
        carId4.setMileage(1);
        carId4.setModel("Model");
        carId4.setPlates("Plates");
        carId4.setProdYear(1);
        carId4.setTrainer(trainer3);

        TrainerEntity dto = getTrainerEntity(carId4);

        // Act
        TrainerEntity actualSaveTrainerResult = trainerService.saveTrainer(dto);

        // Assert
        verify(trainerEntityRepository).save(isA(TrainerEntity.class));
        assertSame(trainerEntity, actualSaveTrainerResult);
    }

    /**
     * Method under test: {@link TrainerService#saveTrainer(TrainerRegistrationDto)}
     */
    @Test
    void testSaveTrainer2() {
        // Arrange
        TrainerEntity trainer = new TrainerEntity();
        trainer.setCarId(new CarEntity());
        trainer.setEmail("jane.doe@example.org");
        trainer.setFirstName("Jane");
        trainer.setIdentifier("42");
        trainer.setLastName("Doe");
        trainer.setLessons(new ArrayList<>());
        trainer.setPassword("iloveyou");
        trainer.setPhoneNumber("6625550144");
        trainer.setStatus(Status.ACTIVE);
        trainer.setStudentsPassRate(1);
        trainer.setTrainTrainerSchedules(new ArrayList<>());
        trainer.setTrainerOpinions(new ArrayList<>());

        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(trainer);

        TrainerEntity trainer2 = new TrainerEntity();
        trainer2.setCarId(carId);
        trainer2.setEmail("jane.doe@example.org");
        trainer2.setFirstName("Jane");
        trainer2.setIdentifier("42");
        trainer2.setLastName("Doe");
        trainer2.setLessons(new ArrayList<>());
        trainer2.setPassword("iloveyou");
        trainer2.setPhoneNumber("6625550144");
        trainer2.setStatus(Status.ACTIVE);
        trainer2.setStudentsPassRate(1);
        trainer2.setTrainTrainerSchedules(new ArrayList<>());
        trainer2.setTrainerOpinions(new ArrayList<>());

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer2);

        TrainerEntity trainerEntity = new TrainerEntity();
        trainerEntity.setCarId(carId2);
        trainerEntity.setEmail("jane.doe@example.org");
        trainerEntity.setFirstName("Jane");
        trainerEntity.setIdentifier("42");
        trainerEntity.setLastName("Doe");
        trainerEntity.setLessons(new ArrayList<>());
        trainerEntity.setPassword("iloveyou");
        trainerEntity.setPhoneNumber("6625550144");
        trainerEntity.setStatus(Status.ACTIVE);
        trainerEntity.setStudentsPassRate(1);
        trainerEntity.setTrainTrainerSchedules(new ArrayList<>());
        trainerEntity.setTrainerOpinions(new ArrayList<>());
        when(trainerEntityRepository.save(Mockito.<TrainerEntity>any())).thenReturn(trainerEntity);

        // Act
        TrainerEntity actualSaveTrainerResult = trainerService
                .saveTrainer(new TrainerRegistrationDto("Jane", "Doe", "42", "6625550144", "jane.doe@example.org","",""));

        // Assert
        verify(trainerEntityRepository).save(isA(TrainerEntity.class));
        assertSame(trainerEntity, actualSaveTrainerResult);
    }

    /**
     * Method under test: {@link TrainerService#getTrainers()}
     */
    @Test
    void testGetTrainers() {
        // Arrange
        when(trainerEntityRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<TrainerEntityDto> actualTrainers = trainerService.getTrainers();

        // Assert
        verify(trainerEntityRepository).findAll();
        assertTrue(actualTrainers.isEmpty());
    }

    /**
     * Method under test: {@link TrainerService#getTrainers()}
     */
    @Test
    void testGetTrainers2() {
        // Arrange
        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(new TrainerEntity());

        TrainerEntity trainer = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);

        ArrayList<TrainerEntity> trainerEntityList = new ArrayList<>();
        trainerEntityList.add(trainerEntity);
        when(trainerEntityRepository.findAll()).thenReturn(trainerEntityList);
        when(trainerMapper.toDto(Mockito.<TrainerEntity>any())).thenThrow(new TrainerNotFoundException());

        // Act and Assert
        assertThrows(TrainerNotFoundException.class, () -> trainerService.getTrainers());
        verify(trainerMapper).toDto(isA(TrainerEntity.class));
        verify(trainerEntityRepository).findAll();
    }

    private static TrainerEntity getTrainerEntity(CarEntity carId) {
        TrainerEntity trainer = new TrainerEntity();
        trainer.setCarId(carId);
        trainer.setEmail("jane.doe@example.org");
        trainer.setFirstName("Jane");
        //        trainer.setId(1L);
        trainer.setIdentifier("42");
        trainer.setLastName("Doe");
        trainer.setLessons(new ArrayList<>());
        trainer.setPassword("iloveyou");
        trainer.setPhoneNumber("6625550144");
        trainer.setStatus(Status.ACTIVE);
        trainer.setStudentsPassRate(1);
        trainer.setTrainTrainerSchedules(new ArrayList<>());
        trainer.setTrainerOpinions(new ArrayList<>());
        return trainer;
    }

    /**
     * Method under test: {@link TrainerService#getTrainerByID(Long)}
     */
    @Test
    void testGetTrainerByID() {
        // Arrange
        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(new TrainerEntity());

        TrainerEntity trainer = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);
        Optional<TrainerEntity> ofResult = Optional.of(trainerEntity);
        when(trainerEntityRepository.findTrainerEntityById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        TrainerEntity actualTrainerByID = trainerService.getTrainerByID(1L);

        // Assert
        verify(trainerEntityRepository).findTrainerEntityById(isA(Long.class));
        assertSame(trainerEntity, actualTrainerByID);
    }

    /**
     * Method under test: {@link TrainerService#getTrainerByID(Long)}
     */
    @Test
    void testGetTrainerByID2() {
        // Arrange
        when(trainerEntityRepository.findTrainerEntityById(Mockito.<Long>any())).thenThrow(new TrainerNotFoundException());

        // Act and Assert
        assertThrows(TrainerNotFoundException.class, () -> trainerService.getTrainerByID(1L));
        verify(trainerEntityRepository).findTrainerEntityById(isA(Long.class));
    }

    /**
     * Method under test: {@link TrainerService#getTrainerByStatus(Status)}
     */
    @Test
    void testGetTrainerByStatus() {
        // Arrange
        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(new TrainerEntity());

        TrainerEntity trainer = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);
        List<TrainerEntity> ofResult = List.of(trainerEntity);
        when(trainerEntityRepository.findAllByStatus(Mockito.<Status>any())).thenReturn(ofResult);
        TrainerEntityDto trainerEntityDto = new TrainerEntityDto("Jane", "Doe", "42", "6625550144", "jane.doe@example.org",
                1, new ArrayList<>());

        when(trainerMapper.toDto(Mockito.<TrainerEntity>any())).thenReturn(trainerEntityDto);

        // Act
        List<TrainerEntityDto> actualTrainerByStatus = trainerService.getTrainerByStatus(Status.ACTIVE);

        // Assert
        verify(trainerMapper).toDto(isA(TrainerEntity.class));
        verify(trainerEntityRepository).findAllByStatus(eq(Status.ACTIVE));
        assertSame(trainerEntityDto, actualTrainerByStatus.getFirst());
    }

    /**
     * Method under test: {@link TrainerService#getTrainerByStatus(Status)}
     */
    @Test
    void testGetTrainerByStatus2() {
        // Arrange
        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(new TrainerEntity());

        TrainerEntity trainer = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);
        List<TrainerEntity> ofResult = List.of(trainerEntity);
        when(trainerEntityRepository.findAllByStatus(Mockito.<Status>any())).thenReturn(ofResult);
        when(trainerMapper.toDto(Mockito.<TrainerEntity>any())).thenThrow(new TrainerNotFoundException());

        // Act and Assert
        assertThrows(TrainerNotFoundException.class, () -> trainerService.getTrainerByStatus(Status.ACTIVE));
        verify(trainerMapper).toDto(isA(TrainerEntity.class));
        verify(trainerEntityRepository).findAllByStatus(eq(Status.ACTIVE));
    }

    /**
     * Method under test: {@link TrainerService#getTrainersByStudentsPassRate()}
     */
    @Test
    void testGetTrainersByStudentsPassRate() {
        // Arrange
        when(trainerEntityRepository.findTrainerEntityByStudentsPassRate()).thenReturn(new ArrayList<>());

        // Act
        List<TrainerEntityDto> actualTrainersByStudentsPassRate = trainerService.getTrainersByStudentsPassRate();

        // Assert
        verify(trainerEntityRepository).findTrainerEntityByStudentsPassRate();
        assertTrue(actualTrainersByStudentsPassRate.isEmpty());
    }

    /**
     * Method under test: {@link TrainerService#getTrainersByStudentsPassRate()}
     */
    @Test
    void testGetTrainersByStudentsPassRate2() {
        // Arrange
        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(new TrainerEntity());

        TrainerEntity trainer = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);

        ArrayList<TrainerEntity> trainerEntityList = new ArrayList<>();
        trainerEntityList.add(trainerEntity);
        when(trainerEntityRepository.findTrainerEntityByStudentsPassRate()).thenReturn(trainerEntityList);
        when(trainerMapper.toDto(Mockito.<TrainerEntity>any())).thenThrow(new TrainerNotFoundException());

        // Act and Assert
        assertThrows(TrainerNotFoundException.class, () -> trainerService.getTrainersByStudentsPassRate());
        verify(trainerMapper).toDto(isA(TrainerEntity.class));
        verify(trainerEntityRepository).findTrainerEntityByStudentsPassRate();
    }

    /**
     * Method under test: {@link TrainerService#getTrainerByIdentifier(String)}
     */
    @Test
    void testGetTrainerByIdentifier() {
        // Arrange
        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(new TrainerEntity());

        TrainerEntity trainer = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);
        Optional<TrainerEntity> ofResult = Optional.of(trainerEntity);
        when(trainerEntityRepository.findTrainerEntityByIdentifier(Mockito.<String>any())).thenReturn(ofResult);
        TrainerEntityDto trainerEntityDto = new TrainerEntityDto("Jane", "Doe", "42", "6625550144", "jane.doe@example.org",
                1, new ArrayList<>());

        when(trainerMapper.toDto(Mockito.<TrainerEntity>any())).thenReturn(trainerEntityDto);

        // Act
        TrainerEntityDto actualTrainerByIdentifier = trainerService.getTrainerByIdentifier("42");

        // Assert
        verify(trainerMapper).toDto(isA(TrainerEntity.class));
        verify(trainerEntityRepository).findTrainerEntityByIdentifier(eq("42"));
        assertSame(trainerEntityDto, actualTrainerByIdentifier);
    }

    /**
     * Method under test: {@link TrainerService#getTrainerByIdentifier(String)}
     */
    @Test
    void testGetTrainerByIdentifier2() {
        // Arrange
        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(new TrainerEntity());

        TrainerEntity trainer = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);
        Optional<TrainerEntity> ofResult = Optional.of(trainerEntity);
        when(trainerEntityRepository.findTrainerEntityByIdentifier(Mockito.<String>any())).thenReturn(ofResult);
        when(trainerMapper.toDto(Mockito.<TrainerEntity>any())).thenThrow(new TrainerNotFoundException());

        // Act and Assert
        assertThrows(TrainerNotFoundException.class, () -> trainerService.getTrainerByIdentifier("42"));
        verify(trainerMapper).toDto(isA(TrainerEntity.class));
        verify(trainerEntityRepository).findTrainerEntityByIdentifier(eq("42"));
    }

    /**
     * Method under test:
     * {@link TrainerService#findAllTrainerLessonsByTrainerID(Long)}
     */
    @Test
    void testFindAllTrainerLessonsByTrainerID() {
        // Arrange
        ArrayList<LessonEntity> lessonEntityList = new ArrayList<>();
        Optional<List<LessonEntity>> ofResult = Optional.of(lessonEntityList);
        when(trainerEntityRepository.findAllLessonsForTrainer(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<LessonEntity> actualFindAllTrainerLessonsByTrainerIDResult = trainerService
                .findAllTrainerLessonsByTrainerID(1L);

        // Assert
        verify(trainerEntityRepository).findAllLessonsForTrainer(isA(Long.class));
        assertTrue(actualFindAllTrainerLessonsByTrainerIDResult.isEmpty());
        assertSame(lessonEntityList, actualFindAllTrainerLessonsByTrainerIDResult);
    }

    /**
     * Method under test:
     * {@link TrainerService#findAllTrainerLessonsByTrainerID(Long)}
     */
    @Test
    void testFindAllTrainerLessonsByTrainerID2() {
        // Arrange
        when(trainerEntityRepository.findAllLessonsForTrainer(Mockito.<Long>any()))
                .thenThrow(new TrainerNotFoundException());

        // Act and Assert
        assertThrows(TrainerNotFoundException.class, () -> trainerService.findAllTrainerLessonsByTrainerID(1L));
        verify(trainerEntityRepository).findAllLessonsForTrainer(isA(Long.class));
    }

    /**
     * Method under test: {@link TrainerService#updateTrainer(Long, Map)}
     */
    @Test
    void testUpdateTrainer() {
        // Arrange
        CarEntity carId = new CarEntity();
        carId.setBrand("Brand");
        carId.setId(1L);
        carId.setLastMaintenanceMileage(1);
        carId.setMileage(1);
        carId.setModel("Model");
        carId.setPlates("Plates");
        carId.setProdYear(1);
        carId.setTrainer(new TrainerEntity());

        TrainerEntity trainer = getTrainerEntity(carId);

        CarEntity carId2 = new CarEntity();
        carId2.setBrand("Brand");
        carId2.setId(1L);
        carId2.setLastMaintenanceMileage(1);
        carId2.setMileage(1);
        carId2.setModel("Model");
        carId2.setPlates("Plates");
        carId2.setProdYear(1);
        carId2.setTrainer(trainer);

        TrainerEntity trainerEntity = getTrainerEntity(carId2);
        Optional<TrainerEntity> ofResult = Optional.of(trainerEntity);
        when(trainerEntityRepository.findTrainerEntityById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        TrainerEntity actualUpdateTrainerResult = trainerService.updateTrainer(1L, new HashMap<>());

        // Assert
        verify(trainerEntityRepository).findTrainerEntityById(isA(Long.class));
        assertSame(trainerEntity, actualUpdateTrainerResult);
    }
}
