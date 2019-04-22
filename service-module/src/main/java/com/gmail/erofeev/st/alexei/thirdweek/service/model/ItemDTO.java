package com.gmail.erofeev.st.alexei.thirdweek.service.model;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDTO {
    private Long id;
    @NotEmpty
    @NotNull
    @Size(max = 40)
    private String name;
    @NotNull
    private ItemStatus itemStatus;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String name, ItemStatus itemStatus) {
        this.id = id;
        this.name = name;
        this.itemStatus = itemStatus;
    }

    public ItemDTO(String name, ItemStatus itemStatus) {
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
