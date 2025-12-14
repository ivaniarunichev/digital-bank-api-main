package com.example.digital_bank_api.validation;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.digital_bank_api.model.AccountType;

@Component
public class FieldsValidator {

    public ResponseEntity<String> validateFields(String holderName,
                                                 String holderCpf,
                                                 BigDecimal balance,
                                                 LocalDate openingDate,
                                                 AccountType accountType) {

        if (holderName == null || holderName.isBlank()) {
            return ResponseEntity.status(400).body("Nombre del titular inválido.");
        }

        if (holderCpf == null || !holderCpf.matches("\\d{11}")) {
            return ResponseEntity.status(400).body("CPF inválido. Debe tener 11 dígitos numéricos.");
        }

        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.status(400).body("El saldo inicial no puede ser negativo.");
        }

        if (openingDate != null && openingDate.isAfter(LocalDate.now())) {
            return ResponseEntity.status(400).body("La fecha de apertura no puede ser futura.");
        }

        if (accountType == null) {
            return ResponseEntity.status(400).body("Tipo de cuenta inválido.");
        }

        return ResponseEntity.ok("OK");
    }
}