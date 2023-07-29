package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Bank;

import java.util.List;

public interface BankRepository {
    List<Bank> getAll();
    Bank getById(Long id);
    int update(Bank bank);
}
