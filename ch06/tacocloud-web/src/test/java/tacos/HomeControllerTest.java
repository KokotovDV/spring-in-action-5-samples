//package java.tacos;
//
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import tacos.data.IngredientRepository;
//import tacos.data.OrderRepository;
//import tacos.data.TacoRepository;
//import tacos.data.UserRepository;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
///**
// * @author Dmitry Kokotov
// */
//@WebMvcTest
//@Disabled("Unsure how to test with @DataJpaTest")
//public class HomeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private IngredientRepository ingredientRepository;
//
//    @MockBean
//    private TacoRepository designRepository;
//
//    @MockBean
//    private OrderRepository orderRepository;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    public void testHomePage() throws Exception {
//        mockMvc.perform(get("/"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("home"))
//            .andExpect(content().string(Matchers.containsString("Welcome to...")));
//    }
//
//}
