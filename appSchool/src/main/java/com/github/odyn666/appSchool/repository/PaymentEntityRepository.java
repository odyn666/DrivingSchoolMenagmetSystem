package com.github.odyn666.appSchool.repository;

import com.github.odyn666.appSchool.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, Long> {
}