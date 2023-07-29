package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Grafik;
import kg.kstu.sweetshop.repository.GrafikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/grafik")
@RequiredArgsConstructor
public class GrafikController {

    private final GrafikRepository grafikRepository;

    @GetMapping
    public ResponseEntity<List<Grafik>> getAll() {
        return ResponseEntity.ok(grafikRepository.getAll());
    }

    @GetMapping("/credit/{id}")
    public ResponseEntity<List<Grafik>> getAllByCreditId(@PathVariable Long id) {
        return ResponseEntity.ok(grafikRepository.getAllByCreditId(id));
    }

    @PostMapping("/pay-credit/{id}")
    public ResponseEntity<String> payCredit(@PathVariable Long id) {
        return ResponseEntity.ok(grafikRepository.payCredit(id));
    }
}
