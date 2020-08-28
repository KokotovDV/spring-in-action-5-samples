package tacos.data;

import tacos.Taco;

/**
 * @author Dmitry Kokotov
 */
public interface TacoRepository {
    Taco save(Taco design);
}
