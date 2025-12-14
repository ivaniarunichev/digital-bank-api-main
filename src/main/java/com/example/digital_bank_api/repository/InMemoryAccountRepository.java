package com.example.digital_bank_api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.digital_bank_api.model.Account;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private final List<Account> data = new ArrayList<>();

    @Override
    public List<Account> findAll() {
        return data;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return data.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Account> findByCpf(String cpf) {
        return data.stream().filter(a -> a.getHolderCpf().equals(cpf)).findFirst();
    }

    @Override
    public Account save(Account account) {
        data.add(account);
        return account;
    }

    @Override
    public void delete(Account account) {
        data.remove(account);
    }
}