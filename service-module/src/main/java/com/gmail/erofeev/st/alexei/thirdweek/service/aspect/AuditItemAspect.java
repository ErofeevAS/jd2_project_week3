package com.gmail.erofeev.st.alexei.thirdweek.service.aspect;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.Status;
import com.gmail.erofeev.st.alexei.thirdweek.repository.model.audit.AuditItem;
import com.gmail.erofeev.st.alexei.thirdweek.service.AuditItemService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

@Aspect
@Component
public class AuditItemAspect {
    private static final Logger logger = LoggerFactory.getLogger(AuditItemAspect.class);
    private final AuditItemService auditItemService;

    @Autowired
    public AuditItemAspect(AuditItemService auditItemService) {
        this.auditItemService = auditItemService;
    }

    @Pointcut("execution(public * com.gmail.erofeev.st.alexei.thirdweek.repository.impl.ItemRepositoryImpl.update(..))")
    public void getNamePointcut() {
    }

    @AfterReturning("getNamePointcut()")
    public void auditAdvice(JoinPoint joinPoint) {
        logger.debug("audit start");
        Object[] args = joinPoint.getArgs();
        Connection connection = (Connection) args[0];
        Long itemId = (Long) args[1];
        Status status = (Status) args[2];
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        AuditItem auditItem = new AuditItem(itemId, status, timestamp);
        auditItemService.save(connection, auditItem);
        logger.debug("audit stopped");
    }
}
