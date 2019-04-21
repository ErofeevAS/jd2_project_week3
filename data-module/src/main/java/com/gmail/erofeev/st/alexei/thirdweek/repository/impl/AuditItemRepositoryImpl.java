package com.gmail.erofeev.st.alexei.thirdweek.repository.impl;

import com.gmail.erofeev.st.alexei.thirdweek.repository.AuditItemRepository;
import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.Status;
import com.gmail.erofeev.st.alexei.thirdweek.repository.exception.DataBaseException;
import com.gmail.erofeev.st.alexei.thirdweek.repository.model.audit.AuditItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository
public class AuditItemRepositoryImpl implements AuditItemRepository {
    private static final Logger logger = LoggerFactory.getLogger(AuditItemRepositoryImpl.class);

    @Override
    public AuditItem save(Connection connection, AuditItem auditItem) {
        Long itemId = auditItem.getItemId();
        Status action = auditItem.getAction();
        Timestamp date = auditItem.getDate();
        String sql = "INSERT INTO audititem (item_id,action,date) values(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, itemId);
            preparedStatement.setString(2, action.name());
            preparedStatement.setTimestamp(3, date);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    auditItem.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataBaseException("Database exception during saving audit for item", e);
        }
        return auditItem;
    }
}
