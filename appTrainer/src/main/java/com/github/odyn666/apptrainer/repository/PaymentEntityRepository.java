package com.github.odyn666.apptrainer.repository;

import com.github.odyn666.apptrainer.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, Long> {
}