package com.gmail.erofeev.st.alexei.thirdweek.repository;

import com.gmail.erofeev.st.alexei.thirdweek.repository.model.Item;
import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;

import java.sql.Connection;
import java.util.List;

public interface ItemRepository {
    Item add(Connection connection, Item item);

    Integer update(Connection connection, Long id, ItemStatus itemStatus);

    List<Item> getItems(Connection connection, int pageNumber, int amount);
}
