package com.gmail.erofeev.st.alexei.thirdweek.service.model;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.Status;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDTO {
    private Long id;
    @NotEmpty
    @NotNull
    @Size(max = 40)
    private String name;
    private Status status;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public ItemDTO(String name, Status status) {
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
