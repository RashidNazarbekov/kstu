package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Measurement;
import kg.kstu.sweetshop.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/measurement")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementRepository measurementRepository;

    @GetMapping
    public ResponseEntity<List<Measurement>> getAll() {
        return ResponseEntity.ok(measurementRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measurement> getById(@PathVariable Long id) {
        return ResponseEntity.ok(measurementRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Measurement measurement) {
        measurementRepository.add(measurement);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Measurement measurement) {
        measurementRepository.update(measurement);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        measurementRepository.delete(id);
        return ResponseEntity.ok(null);
    }
}
