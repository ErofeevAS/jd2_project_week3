package com.gmail.erofeev.st.alexei.thirdweek.service.impl;

import com.gmail.erofeev.st.alexei.thirdweek.repository.AuditItemRepository;
import com.gmail.erofeev.st.alexei.thirdweek.repository.model.audit.AuditItem;
import com.gmail.erofeev.st.alexei.thirdweek.service.AuditItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class AuditItemServiceImpl implements AuditItemService {
    private final AuditItemRepository auditItemRepository;

    @Autowired
    public AuditItemServiceImpl(AuditItemRepository auditItemRepository) {
        this.auditItemRepository = auditItemRepository;
    }

    @Override
    public AuditItem save(Connection connection, AuditItem item) {
        return auditItemRepository.save(connection, item);
    }
}
