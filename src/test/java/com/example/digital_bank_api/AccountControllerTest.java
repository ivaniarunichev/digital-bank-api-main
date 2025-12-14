package com.example.digital_bank_api;

import com.example.digital_bank_api.controller.AccountController;
import com.example.digital_bank_api.model.Account;
import com.example.digital_bank_api.model.AccountType;
import com.example.digital_bank_api.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired MockMvc mockMvc;
    @MockBean
    AccountService accountService;

    @Test
    void getAll_devuelve200() throws Exception {
        Account a = new Account(1L, "Ana", "12345678901",
                new BigDecimal("10.00"), 1, "001", AccountType.CURRENT);

        when(accountService.getAll()).thenReturn(List.of(a));

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk());
    }
}