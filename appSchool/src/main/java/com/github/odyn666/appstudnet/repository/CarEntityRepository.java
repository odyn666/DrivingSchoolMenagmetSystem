package com.github.odyn666.appstudnet.repository;

import com.github.odyn666.appstudnet.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEntityRepository extends JpaRepository<CarEntity, Long> {
}