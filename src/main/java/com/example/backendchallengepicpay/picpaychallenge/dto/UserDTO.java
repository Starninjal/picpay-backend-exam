package com.example.backendchallengepicpay.picpaychallenge.dto;

import com.example.backendchallengepicpay.picpaychallenge.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
