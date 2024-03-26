package com.github.odyn666.appSchool.repository;

import com.github.odyn666.appSchool.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarEntityRepository extends JpaRepository<CarEntity, Long> {
}