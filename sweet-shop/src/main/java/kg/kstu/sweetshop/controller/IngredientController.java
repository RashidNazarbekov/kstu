package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Ingredient;
import kg.kstu.sweetshop.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientRepository.getAll());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<Ingredient>> getAllByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientRepository.getAllByProductId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.add(ingredient));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.update(ingredient));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ingredientRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
