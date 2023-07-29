package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.SaleOfProduct;
import kg.kstu.sweetshop.repository.SaleOfProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sale")
@RequiredArgsConstructor
public class SaleOfProductController {

    private final SaleOfProductRepository saleOfProductRepository;

    @GetMapping
    public ResponseEntity<List<SaleOfProduct>> getAll() {
        return ResponseEntity.ok(saleOfProductRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleOfProduct> getById(@PathVariable Long id) {
        return ResponseEntity.ok(saleOfProductRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody SaleOfProduct saleOfProduct) {
        return ResponseEntity.ok(saleOfProductRepository.add(saleOfProduct));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody SaleOfProduct saleOfProduct) {
        return ResponseEntity.ok(saleOfProductRepository.update(saleOfProduct));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        saleOfProductRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
