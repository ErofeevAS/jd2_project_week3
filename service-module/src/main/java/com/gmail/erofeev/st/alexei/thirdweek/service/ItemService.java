package com.gmail.erofeev.st.alexei.thirdweek.service;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO add(ItemDTO item);

    void update(Long id, ItemStatus itemStatus);

    List<ItemDTO> getItems(int page, int amount);
}
