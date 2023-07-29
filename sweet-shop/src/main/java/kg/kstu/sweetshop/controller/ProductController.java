package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Product;
import kg.kstu.sweetshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Product product) {
        productRepository.add(product);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Product product) {
        productRepository.update(product);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
