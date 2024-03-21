package com.github.odyn666.appstudnet.repository;

import com.github.odyn666.appstudnet.entity.TrainerEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
@FeignClient("TRAINER-SERVICE")
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

    @GetMapping("/trainer/all")
    List<TrainerEntity> findAll();
}
