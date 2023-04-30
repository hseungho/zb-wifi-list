package service.repository;

import service.entity.History;
import service.repository.base.Repository;

import java.util.List;

public class HistoryRepositoryImpl extends Repository implements HistoryRepository {

    @Override
    public void save(History history) {

    }

    @Override
    public History find(int id) {
        return null;
    }

    @Override
    public List<History> findAll() {
        return null;
    }

    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
