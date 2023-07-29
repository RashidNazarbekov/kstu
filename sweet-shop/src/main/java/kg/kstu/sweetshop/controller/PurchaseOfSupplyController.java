package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.PurchaseOfSupply;
import kg.kstu.sweetshop.repository.PurchaseOfSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/purchase")
@RequiredArgsConstructor
public class PurchaseOfSupplyController {

    private final PurchaseOfSupplyRepository purchaseOfSupplyRepository;

    @GetMapping
    public ResponseEntity<List<PurchaseOfSupply>> getAll() {
        return ResponseEntity.ok(purchaseOfSupplyRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOfSupply> getById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOfSupplyRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody PurchaseOfSupply purchase) {
        return ResponseEntity.ok(purchaseOfSupplyRepository.add(purchase));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody PurchaseOfSupply purchase) {
        return ResponseEntity.ok(purchaseOfSupplyRepository.update(purchase));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        purchaseOfSupplyRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
