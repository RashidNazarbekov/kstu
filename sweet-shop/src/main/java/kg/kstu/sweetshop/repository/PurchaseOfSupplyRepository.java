package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.PurchaseOfSupply;

public interface PurchaseOfSupplyRepository extends BaseRepository<PurchaseOfSupply> {
    String add(PurchaseOfSupply purchase);
    String update(PurchaseOfSupply purchase);
}
