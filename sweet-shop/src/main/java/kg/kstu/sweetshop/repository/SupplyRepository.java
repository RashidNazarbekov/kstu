package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Supply;

public interface SupplyRepository extends BaseRepository<Supply> {
    void add(Supply supply);
    void update(Supply supply);
}
