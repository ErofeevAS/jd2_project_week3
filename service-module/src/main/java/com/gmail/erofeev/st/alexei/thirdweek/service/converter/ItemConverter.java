package com.gmail.erofeev.st.alexei.thirdweek.service.converter;

import com.gmail.erofeev.st.alexei.thirdweek.repository.model.Item;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;

import java.util.List;

public interface ItemConverter {
    ItemDTO toDTO(Item item);

    Item toItem(ItemDTO itemDTO);

    List<ItemDTO> toDTOs(List<Item> items);
}
