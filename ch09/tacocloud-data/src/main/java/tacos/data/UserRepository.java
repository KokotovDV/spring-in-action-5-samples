package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.User;
/**
 * @author Dmitry Kokotov
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}

