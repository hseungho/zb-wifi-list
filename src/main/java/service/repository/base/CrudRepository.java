package service.repository.base;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, ID> {

    void save(E e);
    Optional<E> findById(ID id);
    List<E> findAll();
    boolean updateById(ID id);
    boolean deleteById(ID id);

}
