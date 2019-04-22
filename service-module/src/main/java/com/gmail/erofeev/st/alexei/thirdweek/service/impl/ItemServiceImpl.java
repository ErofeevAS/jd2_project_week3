package com.gmail.erofeev.st.alexei.thirdweek.service.impl;

import com.gmail.erofeev.st.alexei.thirdweek.repository.ItemRepository;
import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;
import com.gmail.erofeev.st.alexei.thirdweek.repository.model.Item;
import com.gmail.erofeev.st.alexei.thirdweek.service.ItemService;
import com.gmail.erofeev.st.alexei.thirdweek.service.connection.ConnectionService;
import com.gmail.erofeev.st.alexei.thirdweek.service.converter.ItemConverter;
import com.gmail.erofeev.st.alexei.thirdweek.service.exception.ServiceException;
import com.gmail.erofeev.st.alexei.thirdweek.service.exception.WrongNumberOfReturnValuesException;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    private final ItemRepository itemRepository;
    private final ConnectionService connectionService;
    private final ItemConverter itemConverter;
    private static final int MAX_AMOUNT_OF_UPDATE = 1;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ConnectionService connectionService, ItemConverter itemConverter) {
        this.itemRepository = itemRepository;
        this.connectionService = connectionService;
        this.itemConverter = itemConverter;
    }

    @Override
    public ItemDTO add(ItemDTO itemDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Item item = itemConverter.toItem(itemDTO);
                itemRepository.add(connection, item);
                ItemDTO savedItem = itemConverter.toDTO(item);
                connection.commit();
                return savedItem;
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Can't add item with id: {}", itemDTO.getId());
                throw new ServiceException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error("Can't get connection to database:", e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Long id, ItemStatus itemStatus) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Integer amountOfChanges = itemRepository.update(connection, id, itemStatus);
                if (amountOfChanges > MAX_AMOUNT_OF_UPDATE) {
                    logger.error("Can't update. Too many items with id:{}", id);
                    throw new WrongNumberOfReturnValuesException();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Can't get items with id: " + id + " from database");
                throw new ServiceException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error("Can't get connection to database:", e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ItemDTO> getItems(int page, int amount) {
        List<ItemDTO> itemsDTO;
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                int offset = (page - 1) * amount;
                List<Item> items = itemRepository.getItems(connection, offset, amount);
                itemsDTO = itemConverter.toDTOs(items);
                connection.commit();
                return itemsDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Can't get items with from database");
                throw new ServiceException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error("Can't get connection to database:", e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
