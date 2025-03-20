package com.DistributedSystemsLab2.Bank_app;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {
    private final BankRepository bankRepository;

    public BankAccountController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return bankRepository.findAll();
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable long id) {
        return bankRepository.findById(id)
                .map(account -> ResponseEntity.ok(account.getBalance()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable long id, @RequestParam double amount) {
        return bankRepository.findById(id).map(account -> {
            account.withdraw(amount);
            bankRepository.save(account);
            return ResponseEntity.ok(account);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{name}/add")
    public ResponseEntity<?> addAccount(@PathVariable String name) {
        if (bankRepository.findByName(name).isPresent()) {
            return ResponseEntity.badRequest().body("Bank account already exists");
        }
        BankAccount account = new BankAccount(name, 0.0);
        bankRepository.save(account);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{identifier}/deposit")
    public ResponseEntity<?> deposit(@PathVariable String identifier, @RequestParam double amount) {
        BankAccount account = identifier.matches("\\d+")
                ? bankRepository.findById(Long.parseLong(identifier)).orElse(null)
                : bankRepository.findByName(identifier).orElse(null);

        if (account == null) return ResponseEntity.notFound().build();

        account.deposit(amount);
        bankRepository.save(account);
        return ResponseEntity.ok(account);
    }
}
