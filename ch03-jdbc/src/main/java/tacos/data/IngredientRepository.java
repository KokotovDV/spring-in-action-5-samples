package tacos.data;

import tacos.Ingredient;

/**
 * @author Dmitry Kokotov
 */
public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
