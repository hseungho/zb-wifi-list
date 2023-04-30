package service.repository;

import global.config.InstanceFactory;
import global.constants.SQLConstants;
import service.entity.History;
import service.repository.base.BaseRepository;
import service.repository.base.ConnectionPool;
import service.repository.base.transaction.TransactionalProxy;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoryRepositoryImpl extends BaseRepository<History, Long> implements HistoryRepository {

    public HistoryRepositoryImpl(ConnectionPool connectionPool) {
        super(connectionPool);
        this.DDL(SQLConstants.HISTORY_TABLE.DDL);
    }

    private Connection getTxConnection() {
        return ((TransactionalProxy) Proxy.getInvocationHandler(InstanceFactory.HistoryRepositoryFactory.getInstance())).getConnection();
    }

    @Override
    public void save(History history) {
        String query = SQLConstants.HISTORY_TABLE.INSERT_BASIC_STATEMENT;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            preparedStatement.setObject(1, history.getLat());
            preparedStatement.setObject(2, history.getLnt());
            preparedStatement.setObject(3, history.getCreatedAt().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement, null);
        }
    }

    @Override
    public void update(History entity) {

    }

    @Override
    public void delete(History entity) {
        deleteById(entity.getId());
    }

    private void deleteById(Long id) {
        String query = SQLConstants.HISTORY_TABLE.DELETE_WHERE_ID;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement, null);
        }
    }

    @Override
    public Optional<History> findById(Long id) {

        return null;
    }

    @Override
    public List<History> findAll() {
        String query = SQLConstants.HISTORY_TABLE.SELECT_ALL;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            List<History> historyList = new ArrayList<>();
            while (resultSet.next()) {
                History history = History.of(resultSet);
                historyList.add(history);
            }
            return historyList;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            close(preparedStatement, resultSet);
        }
    }

}
