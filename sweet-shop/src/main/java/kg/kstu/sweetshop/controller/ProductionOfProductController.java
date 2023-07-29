package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.ProductionOfProduct;
import kg.kstu.sweetshop.repository.ProductionOfProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/production")
@RequiredArgsConstructor
public class ProductionOfProductController {

    private final ProductionOfProductRepository productionOfProductRepository;

    @GetMapping
    public ResponseEntity<List<ProductionOfProduct>> getAll() {
        return ResponseEntity.ok(productionOfProductRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionOfProduct> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productionOfProductRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody ProductionOfProduct product) {
        return ResponseEntity.ok(productionOfProductRepository.add(product));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody ProductionOfProduct product) {
        return ResponseEntity.ok(productionOfProductRepository.update(product));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productionOfProductRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
