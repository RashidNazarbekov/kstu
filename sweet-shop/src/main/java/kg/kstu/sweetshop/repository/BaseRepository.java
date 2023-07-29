package kg.kstu.sweetshop.repository;

import java.util.List;

public interface BaseRepository<Model> {
    List<Model> getAll();
    Model getById(Long id);
    void delete(Long id);
}
