package com.gmail.erofeev.st.alexei.thirdweek.repository.model;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;

public class Item {
    private Long id;
    private String name;
    private ItemStatus itemStatus;

    public Item(String name, ItemStatus itemStatus) {
        this.name = name;
        this.itemStatus = itemStatus;
    }

    public Item(Long id, String name, ItemStatus itemStatus) {
        this.id = id;
        this.name = name;
        this.itemStatus = itemStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }
}
