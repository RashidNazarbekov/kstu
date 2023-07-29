package kg.kstu.sweetshop.controller;

import kg.kstu.sweetshop.models.Salary;
import kg.kstu.sweetshop.models.YearMonth;
import kg.kstu.sweetshop.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryRepository salaryRepository;

    @GetMapping
    public ResponseEntity<List<Salary>> getAll() {
        return ResponseEntity.ok(salaryRepository.getAll());
    }

    @GetMapping("/year-month")
    public ResponseEntity<List<Salary>> getAllByYearAndMonth(@RequestParam Integer year,
                                                             @RequestParam String month) {
        return ResponseEntity.ok(salaryRepository.getAllByYearAndMonth(year, month));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salary> getById(@PathVariable Long id) {
        return ResponseEntity.ok(salaryRepository.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody YearMonth yearMonth) {
        salaryRepository.add(yearMonth);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Salary salary) {
        salaryRepository.update(salary);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        salaryRepository.delete(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/issue-salary")
    public ResponseEntity<?> issueSalary(@RequestBody YearMonth yearMonth) {
        return ResponseEntity.ok(salaryRepository.issueSalary(yearMonth));
    }
}
