package com.gmail.erofeev.st.alexei.thirdweek.repository;

import com.gmail.erofeev.st.alexei.thirdweek.repository.model.audit.AuditItem;

import java.sql.Connection;

public interface AuditItemRepository {
    AuditItem save(Connection connection, AuditItem item);
}
