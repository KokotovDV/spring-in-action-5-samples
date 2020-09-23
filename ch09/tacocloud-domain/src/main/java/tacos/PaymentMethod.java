package tacos;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

/**
 * @author Dmitry Kokotov
 */
@Entity
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private final User user;

    private final String ccNumber;
    private final String ccCVV;
    private final String ccExpiration;

}
