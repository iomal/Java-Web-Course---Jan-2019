package chushka.respositories;

import java.util.*;

public interface GenericRepository <E,K> {
   void save(E enitiy);
    Optional<E> findById(K id);
    List<E> getAll();

}
