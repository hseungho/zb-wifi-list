package service.repository;

import service.entity.Wifi;
import service.repository.base.CrudRepository;

import java.util.List;
import java.util.Map;

public interface WifiRepository extends CrudRepository<Wifi> {

    void saveAll(List<Map<String, Object>> mapList);

}
