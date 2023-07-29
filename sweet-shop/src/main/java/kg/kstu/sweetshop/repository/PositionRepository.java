package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Position;

public interface PositionRepository extends BaseRepository<Position> {
    void add(Position position);
    void update(Position position);
}
