package tacos.kitchen;

import tacos.Order;

/**
 * @author Dmitry Kokotov
 */
public interface OrderReceiver {

    Order receiveOrder();
}
