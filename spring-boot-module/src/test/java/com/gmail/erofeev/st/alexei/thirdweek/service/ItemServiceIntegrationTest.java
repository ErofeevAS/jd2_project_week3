package com.gmail.erofeev.st.alexei.thirdweek.service;

import com.gmail.erofeev.st.alexei.thirdweek.controller.app.SpringBootModuleApplication;
import com.gmail.erofeev.st.alexei.thirdweek.controller.impl.ItemController;
import com.gmail.erofeev.st.alexei.thirdweek.repository.DataBaseInitRepository;
import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.Status;
import com.gmail.erofeev.st.alexei.thirdweek.service.connection.ConnectionService;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = SpringBootModuleApplication.class)
public class ItemServiceIntegrationTest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private SQLFileReaderService sqlFileReaderService;
    @Autowired
    private DataBaseInitRepository dataBaseInitRepository;
    @Autowired
    private ConnectionService connectionService;
    @Value("${my.database.init.data.file}")
    private String fileName;
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceIntegrationTest.class);

    @Before
    public void init() throws IOException {
        File file = new ClassPathResource(fileName).getFile();
        String[] queries = sqlFileReaderService.getQueryFromFile(file);
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                dataBaseInitRepository.init(connection, queries);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnItemWIthId() {
        ItemDTO itemDTO = new ItemDTO("test_item", Status.READY);
        itemDTO = itemService.add(itemDTO);
        Assert.assertNotNull(itemDTO.getId());
    }

    @Test
    public void shouldReturnThreeItems() {
        List<ItemDTO> items = itemService.getItems(1, 10);
        Assert.assertTrue(items.size() >= 3);
    }

    @Test
    public void shouldReturnItemWithReadyStatus() {
        itemService.update(1L, Status.READY);
        itemService.update(2L, Status.READY);
        itemService.update(3L, Status.READY);
        List<ItemDTO> items = itemService.getItems(1, 10);
        boolean isCorrectStatus = true;
        for (ItemDTO item : items) {
            if (!item.getStatus().equals(Status.READY)) {
                isCorrectStatus = false;
                break;
            }
        }
        Assert.assertTrue(isCorrectStatus);
    }
}
