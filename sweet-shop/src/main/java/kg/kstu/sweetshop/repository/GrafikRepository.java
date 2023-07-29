package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Grafik;

import java.util.List;

public interface GrafikRepository {
    List<Grafik> getAll();
    List<Grafik> getAllByCreditId(Long creditId);
    String payCredit(Long id);
}
