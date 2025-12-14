package com.example.digital_bank_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    private Long id;
    private String holderName;
    private String holderCpf;
    private BigDecimal balance;
    private int number;
    private String agency;
    private LocalDate openingDate;
    private AccountState accountState;
    private AccountType accountType;

    public Account() {
        // Constructor vacío para JSON
    }

    public Account(Long id, String holderName, String holderCpf, BigDecimal balance,
                   int number, String agency, AccountType accountType) {
        this.id = id;
        this.holderName = holderName;
        this.holderCpf = holderCpf;
        this.balance = balance;
        this.number = number;
        this.agency = agency;
        this.openingDate = LocalDate.now();
        this.accountState = AccountState.ACTIVE;
        this.accountType = accountType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }

    public String getHolderCpf() { return holderCpf; }
    public void setHolderCpf(String holderCpf) { this.holderCpf = holderCpf; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getAgency() { return agency; }
    public void setAgency(String agency) { this.agency = agency; }

    public LocalDate getOpeningDate() { return openingDate; }
    public void setOpeningDate(LocalDate openingDate) { this.openingDate = openingDate; }

    public AccountState getAccountState() { return accountState; }
    public void setAccountState(AccountState accountState) { this.accountState = accountState; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
}