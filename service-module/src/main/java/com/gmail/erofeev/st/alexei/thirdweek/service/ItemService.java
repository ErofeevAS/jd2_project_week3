package com.gmail.erofeev.st.alexei.thirdweek.service;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.Status;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO add(ItemDTO item);

    void update(Long id, Status status);

    List<ItemDTO> getItems(int page, int amount);
}
