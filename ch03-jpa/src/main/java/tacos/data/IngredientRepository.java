package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

/**
 * @author Dmitry Kokotov
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
