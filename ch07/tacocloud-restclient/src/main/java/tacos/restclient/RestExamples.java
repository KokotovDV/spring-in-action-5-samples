package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;
import tacos.Ingredient;
import tacos.Taco;

import java.net.URI;
import java.util.List;

/**
 * @author Dmitry Kokotov
 */
@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {

    public static void main(String[] args) {
        SpringApplication.run(RestExamples.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner fetchIngredients(TacoCloudClient tacoCloudClient){
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("GETTING INGREDIENT BY ID");
            log.info("Ingredient:" + tacoCloudClient.getIngredientByID("CHED"));
            log.info("GETTING ALL INGREDIENTS");
            List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
            log.info("All ingredients:");
            for (Ingredient ingredient: ingredients) {
                log.info("   - " + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner putAnIgredient(TacoCloudClient tacoCloudClient){
        return args -> {
            log.info("----------------------- PUT -------------------------");
            Ingredient before = tacoCloudClient.getIngredientByID("LETC");
            log.info("Before: " + before);
            tacoCloudClient.updateIngredient(
                    new Ingredient("LETC", "Shredded Lettuce", Ingredient.Type.VEGGIES));
            Ingredient after = tacoCloudClient.getIngredientByID("LETC");
            log.info("After" + after);
        };
    }

    @Bean
    public CommandLineRunner addAnIgredient(TacoCloudClient tacoCloudClient){
        return args -> {
            log.info("----------------------- POST ------------------------");
            Ingredient ingredient = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
            Ingredient ingredientAfter = tacoCloudClient.createIngredient(ingredient);
            log.info("After: " + ingredientAfter);
        };
    }

    @Bean
    public CommandLineRunner deleteAnIgredient(TacoCloudClient tacoCloudClient){
        return args -> {
            Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
            tacoCloudClient.createIngredient(beefFajita);
            Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
            tacoCloudClient.createIngredient(shrimp);

            Ingredient before = tacoCloudClient.getIngredientByID("CHIX");
            log.info("Before: " + before);
            tacoCloudClient.deleteIngredient(before);
            Ingredient after = tacoCloudClient.getIngredientByID("CHIX");
            log.info("After: " + after);

            before = tacoCloudClient.getIngredientByID("BFFJ");
            log.info("Before: " + before);
            tacoCloudClient.deleteIngredient(before);
            after = tacoCloudClient.getIngredientByID("BFFJ");
            log.info("After: " + after);

            before = tacoCloudClient.getIngredientByID("SHMP");
            log.info("Before: " + before);
            tacoCloudClient.deleteIngredient(before);
            after = tacoCloudClient.getIngredientByID("SHMP");
            log.info("After: " + after);
        };
    }

    @Bean
    public Traverson traverson(){
        Traverson traverson = new Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
        return traverson;
    }

    @Bean
    public CommandLineRunner traversonGetIngredients(TacoCloudClient tacoCloudClient){
        return args -> {
            Iterable<Ingredient> ingredients = tacoCloudClient.getAllIngredientsWithTraverson();
            log.info("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------");
            for (Ingredient ingredient: ingredients){
                log.info("   - " + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner traversonSaveIngredients(TacoCloudClient tacoCloudClient){
        return args -> {
            Ingredient pico = tacoCloudClient.addIngredient(
                    new Ingredient("PICO", "Pico de Gallo", Ingredient.Type.SAUCE));
            Iterable<Ingredient> ingredients = tacoCloudClient.getAllIngredientsWithTraverson();
            log.info("----------------------- ALL INGREDIENTS AFTER SAVING PICO -------------------------");
            for (Ingredient ingredient: ingredients){
                log.info("   - " + ingredient);
            }
            tacoCloudClient.deleteIngredient(pico);
        };
    }

    @Bean
    public CommandLineRunner traversonRecentTacos(TacoCloudClient tacoCloudClient) {
        return args -> {
            Iterable<Taco> recentTacos = tacoCloudClient.getRecentTacosWithTraverson();
            log.info("----------------------- GET RECENT TACOS WITH TRAVERSON -------------------------");
            for (Taco taco : recentTacos) {
                log.info("   -  " + taco);
            }
        };
    }

}
