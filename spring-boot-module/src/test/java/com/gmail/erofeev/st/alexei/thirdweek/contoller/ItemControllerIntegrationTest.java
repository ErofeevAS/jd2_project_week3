package com.gmail.erofeev.st.alexei.thirdweek.contoller;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gmail.erofeev.st.alexei.thirdweek.controller.app.SpringBootModuleApplication;
import com.gmail.erofeev.st.alexei.thirdweek.controller.impl.ItemController;
import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.Status;
import com.gmail.erofeev.st.alexei.thirdweek.service.ItemService;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = SpringBootModuleApplication.class)
public class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private WebClient webClient;

    @MockBean
    private ItemService itemService;

    private List<ItemDTO> items = asList(
            new ItemDTO(1L, "pen", Status.COMPLETED),
            new ItemDTO(2L, "apple", Status.READY));

    @Before
    public void init() {
        when(itemService.getItems(1, 10)).thenReturn(items);
        webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc)
                .useMockMvcForHosts("app.com")
                .build();
    }

    @Test
    public void requestForUsersIsSuccessfullyProcessedWithAvailableUsersList() throws Exception {
        this.mockMvc.perform(get("/items").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(allOf(
                        containsString("pen"),
                        containsString("apple")))
                );
    }
}
