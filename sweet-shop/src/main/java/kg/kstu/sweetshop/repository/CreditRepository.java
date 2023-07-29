package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Credit;

public interface CreditRepository extends BaseRepository<Credit> {
    String add(Credit credit);
}
