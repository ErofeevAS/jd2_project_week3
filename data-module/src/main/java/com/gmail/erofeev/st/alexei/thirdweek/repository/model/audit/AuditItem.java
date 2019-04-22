package com.gmail.erofeev.st.alexei.thirdweek.repository.model.audit;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;

import java.sql.Timestamp;

public class AuditItem {
    private Long id;
    private Long itemId;
    private ItemStatus action;
    private Timestamp date;

    public AuditItem(Long itemId, ItemStatus action, Timestamp date) {
        this.itemId = itemId;
        this.action = action;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ItemStatus getAction() {
        return action;
    }

    public void setAction(ItemStatus action) {
        this.action = action;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
