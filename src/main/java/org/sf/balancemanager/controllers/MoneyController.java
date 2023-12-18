package org.sf.balancemanager.controllers;

import lombok.RequiredArgsConstructor;
import org.sf.balancemanager.entities.MoneyDTO;
import org.sf.balancemanager.services.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MoneyController {

    private final UserAccountService userAccountService;

    @GetMapping("/getBalance")
    public ResponseEntity<MoneyDTO> getBalance(Long userId) {
        Double balance;

        try {
            balance = userAccountService.getBalance(userId);
        } catch (NoSuchFieldException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MoneyDTO(-1.0, e.getMessage()));
        }

        return ResponseEntity.ok(new MoneyDTO(balance, ""));
    }

    @GetMapping("/takeMoney")
    public ResponseEntity<MoneyDTO> takeMoney(Long userId, Double amount) {
        try {
            userAccountService.takeMoney(userId, amount);
            return ResponseEntity.ok(new MoneyDTO(1.0, ""));
        } catch (NoSuchFieldException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MoneyDTO(0.0, e.getMessage()));
        }
    }

    @GetMapping("/putMoney")
    public ResponseEntity<MoneyDTO> putMoney(Long userId, Double amount) {
        try {
            userAccountService.putMoney(userId, amount);
            return ResponseEntity.ok(new MoneyDTO(1.0, ""));
        } catch (NoSuchFieldException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MoneyDTO(0.0, e.getMessage()));
        }
    }

    @GetMapping("/transferMoney")
    public ResponseEntity<MoneyDTO> transferMoney(Long userId1, Long userId2, Double amount) {
        try {
            userAccountService.transferMoney(userId1, userId2, amount);
            return ResponseEntity.ok(new MoneyDTO(1.0, ""));
        } catch (NoSuchFieldException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MoneyDTO(0.0, e.getMessage()));
        }
    }
}
