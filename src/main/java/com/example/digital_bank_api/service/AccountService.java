package com.example.digital_bank_api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.digital_bank_api.dto.CashoutRequest;
import com.example.digital_bank_api.dto.DepositRequest;
import com.example.digital_bank_api.model.Account;
import com.example.digital_bank_api.repository.AccountRepository;
import com.example.digital_bank_api.validation.FieldsValidator;

@Service
public class AccountService {

    private final AccountRepository repository;
    private final FieldsValidator validator;

    public AccountService(AccountRepository repository, FieldsValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Optional<Account> getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Optional<Account> getById(Long id) {
        return repository.findById(id);
    }

    public ResponseEntity<String> create(Account account) {
        ResponseEntity<String> validation = validator.validateFields(
                account.getHolderName(),
                account.getHolderCpf(),
                account.getBalance(),
                account.getOpeningDate() == null ? LocalDate.now() : account.getOpeningDate(),
                account.getAccountType()
        );

        if (validation.getStatusCode().isError()) {
            return validation;
        }

        if (repository.findById(account.getId()).isPresent()) {
            return ResponseEntity.status(400).body("Ya existe una cuenta con ese id.");
        }

        if (repository.findByCpf(account.getHolderCpf()).isPresent()) {
            return ResponseEntity.status(400).body("Ya existe una cuenta con ese CPF.");
        }

        repository.save(account);
        return ResponseEntity.status(201).body("Cuenta creada correctamente.");
    }

    public ResponseEntity<String> delete(Long id) {
        Optional<Account> accOpt = repository.findById(id);
        if (accOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Cuenta no encontrada.");
        }
        repository.delete(accOpt.get());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<String> deposit(Long id, DepositRequest req) {
        if (req == null || req.getValue() == null || req.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.status(400).body("El importe a ingresar debe ser positivo.");
        }

        Optional<Account> accOpt = repository.findById(id);
        if (accOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Cuenta no encontrada.");
        }

        Account acc = accOpt.get();
        acc.setBalance(acc.getBalance().add(req.getValue()));
        return ResponseEntity.ok("Ingreso realizado. Nuevo saldo: " + acc.getBalance());
    }

    public ResponseEntity<String> cashout(Long id, CashoutRequest req) {
        if (req == null || req.getValue() == null || req.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.status(400).body("El importe a retirar debe ser positivo.");
        }

        Optional<Account> accOpt = repository.findById(id);
        if (accOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Cuenta no encontrada.");
        }

        Account acc = accOpt.get();
        if (acc.getBalance().compareTo(req.getValue()) < 0) {
            return ResponseEntity.status(400).body("Saldo insuficiente.");
        }

        acc.setBalance(acc.getBalance().subtract(req.getValue()));
        return ResponseEntity.ok("Retirada realizada. Nuevo saldo: " + acc.getBalance());
    }
}