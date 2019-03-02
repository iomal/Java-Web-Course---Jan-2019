package metube.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E, K> {
    void save(E entity);

    List<E> findAll();

    Optional<E> findById(K id);
}
