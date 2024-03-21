package com.github.odyn666.appstudnet.repository;

import com.github.odyn666.appstudnet.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, Long> {
}