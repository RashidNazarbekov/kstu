package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Position;
import kg.kstu.sweetshop.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionRepository positionRepository;

    @GetMapping
    public ResponseEntity<List<Position>> getAll() {
        return ResponseEntity.ok(positionRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable Long id) {
        return ResponseEntity.ok(positionRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Position position) {
        positionRepository.add(position);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Position position) {
        positionRepository.update(position);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        positionRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
