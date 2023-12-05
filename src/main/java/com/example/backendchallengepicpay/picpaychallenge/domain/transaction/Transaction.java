package com.example.backendchallengepicpay.picpaychallenge.domain.transaction;

import com.example.backendchallengepicpay.picpaychallenge.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
}
