package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Credit;
import kg.kstu.sweetshop.models.Product;
import kg.kstu.sweetshop.repository.CreditRepository;
import kg.kstu.sweetshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/credit")
@RequiredArgsConstructor
public class CreditController {

    private final CreditRepository creditRepository;

    @GetMapping
    public ResponseEntity<List<Credit>> getAll() {
        return ResponseEntity.ok(creditRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credit> getById(@PathVariable Long id) {
        return ResponseEntity.ok(creditRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Credit credit) {
        return ResponseEntity.ok( creditRepository.add(credit));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        creditRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
