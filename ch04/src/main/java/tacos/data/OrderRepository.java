package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Order;

/**
 * @author Dmitry Kokotov
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}
