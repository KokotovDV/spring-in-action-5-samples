package tacos.restclient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tacos.Ingredient;
import tacos.Taco;

import java.util.List;

/**
 * @author Dmitry Kokotov
 */
@Service
public class TacoCloudClient {
    private RestTemplate rest;
    private Traverson traverson;

    public TacoCloudClient(RestTemplate rest, Traverson traverson) {
        this.rest = rest;
        this.traverson = traverson;
    }

    public Ingredient getIngredientByID(String ingredientId){
        return rest.getForObject("http://localhost:8080/api/ingredients/{id}", Ingredient.class, ingredientId);
    }

    /*
     * Alternate implementations...
     * The next three methods are alternative implementations of
     * getIngredientById() as shown in chapter 6. If you'd like to try
     * any of them out, comment out the previous method and uncomment
     * the variant you want to use.
     */

    /*
     * Specify parameters with a map
     */
    // public Ingredient getIngredientById(String ingredientId) {
    //   Map<String, String> urlVariables = new HashMap<>();
    //   urlVariables.put("id", ingredientId);
    //   return rest.getForObject("http://localhost:8080/ingredients/{id}",
    //       Ingredient.class, urlVariables);
    // }

    /*
     * Request with URI instead of String
     */
    // public Ingredient getIngredientById(String ingredientId) {
    //   Map<String, String> urlVariables = new HashMap<>();
    //   urlVariables.put("id", ingredientId);
    //   URI url = UriComponentsBuilder
    //             .fromHttpUrl("http://localhost:8080/ingredients/{id}")
    //             .build(urlVariables);
    //   return rest.getForObject(url, Ingredient.class);
    // }

    /*
     * Use getForEntity() instead of getForObject()
     */
    // public Ingredient getIngredientById(String ingredientId) {
    //   ResponseEntity<Ingredient> responseEntity =
    //       rest.getForEntity("http://localhost:8080/ingredients/{id}",
    //           Ingredient.class, ingredientId);
    //   log.info("Fetched time: " +
    //           responseEntity.getHeaders().getDate());
    //   return responseEntity.getBody();
    // }

    public List<Ingredient> getAllIngredients(){
        return rest.exchange("http://localhost:8080/ingredients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {}).getBody();
    }

    public void updateIngredient(Ingredient ingredient){
        rest.put("http://localhost:8080/ingredient/{id}", ingredient, ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient){
        return rest.postForObject("http://localhost:8080/api/ingredients",  ingredient, Ingredient.class);
    }

    public void deleteIngredient(Ingredient ingredient){
        rest.delete("http://localhost:8080/api/ingredients/{id}",ingredient.getId());

    }

    public Iterable<Ingredient> getAllIngredientsWithTraverson(){
        ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType =
                new ParameterizedTypeReference<CollectionModel<Ingredient>>() {};

        CollectionModel<Ingredient> ingredientCollectionModel =
                traverson
                        .follow("ingredients")
                        .toObject(ingredientType);

        return ingredientCollectionModel.getContent();
    }

    public Ingredient addIngredient(Ingredient ingredient){
        String ingredientsUrl = traverson
                .follow("ingredients")
                .asLink()
                .getHref();
        return rest.postForObject(ingredientsUrl, ingredient, Ingredient.class);
    }

    public Iterable<Taco> getRecentTacosWithTraverson(){
        ParameterizedTypeReference<CollectionModel<Taco>> tacoType =
                new ParameterizedTypeReference<CollectionModel<Taco>>() {};

        CollectionModel<Taco> tacoCollectionModel =
                traverson
                    .follow("tacos")
                    .follow("recents")
                    .toObject(tacoType);

        return tacoCollectionModel.getContent();
    }
}
