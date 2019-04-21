package com.gmail.erofeev.st.alexei.thirdweek.service;

import com.gmail.erofeev.st.alexei.thirdweek.repository.model.audit.AuditItem;

import java.sql.Connection;

public interface AuditItemService {
    AuditItem save(Connection connection, AuditItem item);
}
