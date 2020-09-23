package tacos.email;

import lombok.Data;

import java.util.List;

/**
 * @author Dmitry Kokotov
 */
@Data
public class Taco {
    private final String name;
    private List<String> ingredients;
}
