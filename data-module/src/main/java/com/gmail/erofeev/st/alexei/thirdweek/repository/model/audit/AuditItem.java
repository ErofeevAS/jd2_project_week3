package com.gmail.erofeev.st.alexei.thirdweek.repository.model.audit;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.Status;

import java.sql.Timestamp;

public class AuditItem {
    private Long id;
    private Long itemId;
    private Status action;
    private Timestamp date;

    public AuditItem(Long itemId, Status action, Timestamp date) {
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

    public Status getAction() {
        return action;
    }

    public void setAction(Status action) {
        this.action = action;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
