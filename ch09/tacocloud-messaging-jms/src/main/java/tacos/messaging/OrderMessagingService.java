package tacos.messaging;

import tacos.Order;

/**
 * @author Dmitry Kokotov
 */
public interface OrderMessagingService {
    void sendOrder(Order order);
}
