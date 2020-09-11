package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import tacos.Taco;
/**
 * @author Dmitry Kokotov
 */
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
