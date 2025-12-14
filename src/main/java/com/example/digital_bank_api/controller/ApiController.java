package com.example.digital_bank_api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> getApiDescription() {
        Map<String, Object> response = new HashMap<>();
        response.put("estado", "API en ejecución");
        response.put("descripcion", "API de cuentas bancarias (demo) con Spring Boot");
        return ResponseEntity.ok(response);
    }
}