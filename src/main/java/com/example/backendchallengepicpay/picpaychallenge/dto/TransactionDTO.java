package com.example.backendchallengepicpay.picpaychallenge.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
}
