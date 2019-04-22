package com.gmail.erofeev.st.alexei.thirdweek.repository.impl;

import com.gmail.erofeev.st.alexei.thirdweek.repository.ItemRepository;
import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;
import com.gmail.erofeev.st.alexei.thirdweek.repository.exception.DataBaseException;
import com.gmail.erofeev.st.alexei.thirdweek.repository.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private static final Logger logger = LoggerFactory.getLogger(ItemRepositoryImpl.class);

    @Override
    public Item add(Connection connection, Item item) {
        String name = item.getName();
        String status = item.getItemStatus().name();
        String sql = "INSERT INTO items (name,status) values(?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, status);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    item.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataBaseException("Database exception during saving a item", e);
        }
        return item;
    }

    @Override
    public Integer update(Connection connection, Long id, ItemStatus itemStatus) {
        String query = "UPDATE  items SET status=? WHERE id=?;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, itemStatus.name());
            ps.setLong(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            String message = "Can't update itemStatus for item. id: " + id;
            logger.error(message);
            throw new DataBaseException(message, e);
        }
    }

    @Override
    public List<Item> getItems(Connection connection, int offset, int amount) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM ITEMS WHERE deleted = false LIMIT ?,? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, amount);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item item = getItem(rs);
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DataBaseException(e.getMessage(), e);
        }
        return items;
    }

    private Item getItem(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String status = resultSet.getString("status");
        ItemStatus itemStatusEum = ItemStatus.valueOf(status);
        return new Item(id, name, itemStatusEum);
    }
}
