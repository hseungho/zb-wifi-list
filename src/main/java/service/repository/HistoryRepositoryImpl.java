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

    @Override
    protected Connection getTxConnection() {
        return ((TransactionalProxy) Proxy.getInvocationHandler(InstanceFactory.HistoryRepositoryFactory.getInstance())).getConnection();
    }

    @Override
    public History save(History history) {
        String query = SQLConstants.HISTORY_TABLE.INSERT_BASIC_STATEMENT;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            super.executeUpdate(preparedStatement,
                    history.getLat(),
                    history.getLnt(),
                    history.getCreatedAt().toString());

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                history.setId(id);
                return history;
            } else {
                throw new RuntimeException("Cannot get History id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public Optional<History> findById(Long id) {
        String query = SQLConstants.HISTORY_TABLE.SELECT_WHERE_ID;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = super.executeQuery(preparedStatement, id);
            if (resultSet.next()) {
                History history = History.of(resultSet);
                return Optional.of(history);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement, resultSet);
        }
        return Optional.empty();
    }

    @Override
    public List<History> findAll() {
        String query = SQLConstants.HISTORY_TABLE.SELECT_ALL;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = super.executeQuery(preparedStatement);
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
            super.executeUpdate(preparedStatement, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
        }
    }

}
