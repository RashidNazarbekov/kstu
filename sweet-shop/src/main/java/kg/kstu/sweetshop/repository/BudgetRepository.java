package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Budget;

import java.util.List;

public interface BudgetRepository {
    List<Budget> getAll();
    Budget getById(Long id);
    int update(Budget budget);
}
