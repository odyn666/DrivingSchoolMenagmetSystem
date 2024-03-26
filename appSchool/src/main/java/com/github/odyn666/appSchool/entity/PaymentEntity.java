package com.github.odyn666.appSchool.entity;

import com.github.odyn666.appSchool.entity.enums.PaymentStatus;
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
@Table(name = "PAYMENTS")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAYMENT_UUID")
    private UUID paymentUUID;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private StudentEntity student;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "HOURS_BOUGHT")
    private Integer hoursBought;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "status")
    private PaymentStatus status;

}
