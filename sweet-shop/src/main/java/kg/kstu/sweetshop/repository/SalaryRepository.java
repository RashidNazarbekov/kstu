package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Salary;
import kg.kstu.sweetshop.models.YearMonth;

import java.util.List;

public interface SalaryRepository extends BaseRepository<Salary> {
    List<Salary> getAllByYearAndMonth(Integer year, String month);
    void add(YearMonth yearMonth);
    void update(Salary salary);
    String issueSalary(YearMonth yearMonth);
}
