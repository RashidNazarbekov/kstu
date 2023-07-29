package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.ProductionOfProduct;

public interface ProductionOfProductRepository extends BaseRepository<ProductionOfProduct> {
    String add(ProductionOfProduct product);
    String update(ProductionOfProduct product);
}
