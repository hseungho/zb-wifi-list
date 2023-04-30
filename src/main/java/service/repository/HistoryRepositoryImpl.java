package service.repository;

import global.constants.SQLConstants;
import service.entity.History;
import service.repository.base.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoryRepositoryImpl extends Repository implements HistoryRepository {

    public HistoryRepositoryImpl() {
        super.connect("History");
        super.initEachTable(SQLConstants.HISTORY_TABLE.DDL);
    }

    @Override
    public void save(History history) {
        String query = SQLConstants.HISTORY_TABLE.INSERT_BASIC_STATEMENT;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(query);

            preparedStatement.setObject(1, history.getLat());
            preparedStatement.setObject(2, history.getLnt());
            preparedStatement.setObject(3, history.getCreatedAt().toString());

            super.executeUpdate(preparedStatement);

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                history.setId(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public Optional<History> findById(Long id) {

        return null;
    }

    @Override
    public List<History> findAll() {
        String query = SQLConstants.HISTORY_TABLE.SELECT_ALL;

        ResultSet rs = super.findQuery(query);

        List<History> historyList = new ArrayList<>();
        try {
            while (rs.next()) {
                History history = History.of(rs);
                historyList.add(history);
                if (!rs.next()) break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historyList;
    }

    @Override
    public boolean updateById(Long id) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String query = SQLConstants.HISTORY_TABLE.DELETE_WHERE_ID;
        return super.deleteById(query, id);
    }
}
