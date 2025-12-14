package com.example.digital_bank_api;

import com.example.digital_bank_api.model.Account;
import com.example.digital_bank_api.model.AccountType;
import com.example.digital_bank_api.repository.AccountRepository;
import com.example.digital_bank_api.service.AccountService;
import com.example.digital_bank_api.validation.FieldsValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock AccountRepository repository;
    @Mock FieldsValidator validator;

    @InjectMocks
    AccountService service;

    @Test
    void create_cuentaValida_guardaEnRepositorio() {
        Account acc = new Account(1L, "Ana", "12345678901",
                new BigDecimal("10.00"), 1, "001", AccountType.CURRENT);
        acc.setOpeningDate(LocalDate.now());

        when(validator.validateFields(anyString(), anyString(), any(), any(), any()))
                .thenReturn(ResponseEntity.ok("OK"));
        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(repository.findByCpf("12345678901")).thenReturn(Optional.empty());

        service.create(acc);

        verify(repository).save(acc);
    }
}