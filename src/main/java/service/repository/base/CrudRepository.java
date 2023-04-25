package service.repository.base;

import java.util.List;

public interface CrudRepository<E> {

    void save(E e);
    E find(int id);
    List<E> findAll();
    boolean update(int id);
    boolean delete(int id);

}
