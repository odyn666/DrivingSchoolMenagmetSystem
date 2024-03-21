package com.github.odyn666.appstudnet.entity;

import com.github.odyn666.appstudnet.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAYMENT_UUID")
    private UUID paymentUUID;


    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "HOURS_BOUGHT")
    private Integer hoursBought;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PaymentStatus status;


}
