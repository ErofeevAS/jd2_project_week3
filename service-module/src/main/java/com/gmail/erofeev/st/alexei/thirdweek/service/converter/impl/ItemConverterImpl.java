package com.gmail.erofeev.st.alexei.thirdweek.service.converter.impl;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;
import com.gmail.erofeev.st.alexei.thirdweek.repository.model.Item;
import com.gmail.erofeev.st.alexei.thirdweek.service.converter.ItemConverter;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemConverterImpl implements ItemConverter {
    @Override
    public ItemDTO toDTO(Item item) {
        Long id = item.getId();
        String name = item.getName();
        ItemStatus itemStatus = item.getItemStatus();
        return new ItemDTO(id, name, itemStatus);
    }

    @Override
    public Item toItem(ItemDTO itemDTO) {
        Long id = itemDTO.getId();
        String name = itemDTO.getName();
        ItemStatus itemStatus = itemDTO.getItemStatus();
        return new Item(id, name, itemStatus);
    }

    @Override
    public List<ItemDTO> toDTOs(List<Item> items) {
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for (Item item : items) {
            itemsDTO.add(toDTO(item));
        }
        return itemsDTO;
    }
}
