package com.gmail.erofeev.st.alexei.thirdweek.repository.model;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.Status;

public class Item {
    private Long id;
    private String name;
    private Status status;

    public Item(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public Item(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
