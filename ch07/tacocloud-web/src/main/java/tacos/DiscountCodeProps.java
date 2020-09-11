package tacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Kokotov
 */
@Component
@ConfigurationProperties(prefix = "taco.discount")
@Data
public class DiscountCodeProps {
    private Map<String, Integer> codes = new HashMap<>();
}
