package com.example.digital_bank_api.repository;

import java.util.List;
import java.util.Optional;

import com.example.digital_bank_api.model.Account;

public interface AccountRepository {
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Optional<Account> findByCpf(String cpf);
    Account save(Account account);
    void delete(Account account);
}