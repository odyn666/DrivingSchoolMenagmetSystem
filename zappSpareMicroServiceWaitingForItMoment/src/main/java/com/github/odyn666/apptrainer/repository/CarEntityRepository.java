package com.github.odyn666.apptrainer.repository;

import com.github.odyn666.apptrainer.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEntityRepository extends JpaRepository<CarEntity, Long> {
}