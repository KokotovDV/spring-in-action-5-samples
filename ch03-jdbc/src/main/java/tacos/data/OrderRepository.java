package tacos.data;

import tacos.Order;

/**
 * @author Dmitry Kokotov
 */
public interface OrderRepository {
    Order save(Order order);
}
