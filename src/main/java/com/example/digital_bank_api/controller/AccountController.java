package com.example.digital_bank_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.digital_bank_api.dto.CashoutRequest;
import com.example.digital_bank_api.dto.DepositRequest;
import com.example.digital_bank_api.model.Account;
import com.example.digital_bank_api.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Account> accounts = accountService.getAll();
        if (accounts.isEmpty()) {
            return ResponseEntity.status(404).body("No hay cuentas registradas.");
        }
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> getByCpf(@PathVariable String cpf) {
        return accountService.getByCpf(cpf)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Cuenta no encontrada."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return accountService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Cuenta no encontrada."));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Account account) {
        return accountService.create(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return accountService.delete(id);
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<?> deposit(@PathVariable Long id, @RequestBody DepositRequest req) {
        return accountService.deposit(id, req);
    }

    @PostMapping("/cashout/{id}")
    public ResponseEntity<?> cashout(@PathVariable Long id, @RequestBody CashoutRequest req) {
        return accountService.cashout(id, req);
    }
}