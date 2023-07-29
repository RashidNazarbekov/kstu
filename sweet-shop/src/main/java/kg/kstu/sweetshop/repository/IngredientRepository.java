package kg.kstu.sweetshop.repository;

import kg.kstu.sweetshop.models.Ingredient;

import java.util.List;

public interface IngredientRepository extends BaseRepository<Ingredient> {
    List<Ingredient> getAllByProductId(Long id);
    String add(Ingredient ingredient);
    String update(Ingredient ingredient);
}
