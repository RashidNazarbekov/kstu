package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Budget;
import kg.kstu.sweetshop.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetRepository budgetRepository;

    @GetMapping
    public ResponseEntity<List<Budget>> getAll() {
        return ResponseEntity.ok(budgetRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getById(@PathVariable Long id) {
        return ResponseEntity.ok(budgetRepository.getById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetRepository.update(budget));
    }
}
