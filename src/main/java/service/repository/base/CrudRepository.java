package service.repository.base;

import java.util.List;

public interface CrudRepository<E> {

    void save(E e);
    E find(Long id);
    List<E> findAll();
    boolean update(Long id);
    boolean delete(Long id);

}
