package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Product;

public interface ProductRepository extends BaseRepository<Product> {
    void add(Product product);
    void update(Product product);
}
