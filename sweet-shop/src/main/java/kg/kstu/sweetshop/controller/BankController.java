package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Bank;
import kg.kstu.sweetshop.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bank")
@RequiredArgsConstructor
public class BankController {

    private final BankRepository bankRepository;

    @GetMapping
    public ResponseEntity<List<Bank>> getAll() {
        return ResponseEntity.ok(bankRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bankRepository.getById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody Bank bank) {
        return ResponseEntity.ok(bankRepository.update(bank));
    }
}
