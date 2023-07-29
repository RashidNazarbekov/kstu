package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.SaleOfProduct;

public interface SaleOfProductRepository extends BaseRepository<SaleOfProduct> {
    String add(SaleOfProduct saleOfProduct);
    String update(SaleOfProduct saleOfProduct);
}
