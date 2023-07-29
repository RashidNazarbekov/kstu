package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Measurement;

public interface MeasurementRepository extends BaseRepository<Measurement> {
    void add(Measurement measurement);
    void update(Measurement measurement);
}
