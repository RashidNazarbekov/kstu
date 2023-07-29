package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Supply;
import kg.kstu.sweetshop.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/supply")
@RequiredArgsConstructor
public class SupplyController {

    private final SupplyRepository supplyRepository;

    @GetMapping
    public ResponseEntity<List<Supply>> getAll() {
        return ResponseEntity.ok(supplyRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supply> getById(@PathVariable Long id) {
        return ResponseEntity.ok(supplyRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Supply supply) {
        supplyRepository.add(supply);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Supply supply) {
        supplyRepository.update(supply);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        supplyRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
