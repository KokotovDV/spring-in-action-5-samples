package tacos.web.api;

import org.springframework.stereotype.Service;
import tacos.*;
import tacos.data.IngredientRepository;
import tacos.data.PaymentMethodRepository;
import tacos.data.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static tacos.web.api.EmailOrder.EmailTaco;

/**
 * @author Dmitry Kokotov
 */
@Service
public class EmailOrderService {

    private UserRepository userRepository;
    private IngredientRepository ingredientRepository;
    private PaymentMethodRepository paymentMethodRepository;

    public EmailOrderService(UserRepository userRepository,
                             IngredientRepository ingredientRepository,
                             PaymentMethodRepository paymentMethodRepository) {
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public Order convertEmailOrderToDomainOrder(EmailOrder emailOrder){
        User user = userRepository.findByEmail(emailOrder.getEmail());
        PaymentMethod paymentMethod = paymentMethodRepository.findByUserId(user.getId());

        Order order = new Order();
        order.setCcNumber(paymentMethod.getCcNumber());
        order.setCcCVV(paymentMethod.getCcCVV());
        order.setCcExpiration(paymentMethod.getCcExpiration());
        order.setDeliveryName(user.getFullname());
        order.setDeliveryStreet(user.getStreet());
        order.setDeliveryCity(user.getCity());
        order.setDeliveryState(user.getState());
        order.setDeliveryZip(user.getZip());
        order.setPlacedAt(new Date());

        // TODO: Handle unhappy case where a given ingredient doesn't match

        List<EmailTaco> emailTacos = emailOrder.getTacos();
        for(EmailTaco emailTaco: emailTacos){
            Taco taco = new Taco();
            taco.setName(emailTaco.getName());
            taco.setCreatedAt(new Date());
            List<String> ingredientIds = emailTaco.getIngredients();
            List<Ingredient> ingredients = new ArrayList<>();
            for (String ingredientId: ingredientIds)
            {
                Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);
                optionalIngredient.ifPresent(ingredients::add);
            }
            taco.setIngredients(ingredients);
            order.addDesign(taco);
        }

        return order;

    }
}
