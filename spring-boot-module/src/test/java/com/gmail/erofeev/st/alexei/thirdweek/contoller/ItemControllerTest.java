package com.gmail.erofeev.st.alexei.thirdweek.contoller;

import com.gmail.erofeev.st.alexei.thirdweek.controller.impl.ItemController;
import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;
import com.gmail.erofeev.st.alexei.thirdweek.service.ItemService;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

    @Mock
    private ItemService itemService;
    private ItemController controller;
    private MockMvc mockMvc;

    private List<ItemDTO> items = asList(
            new ItemDTO(1L, "pen", ItemStatus.COMPLETED),
            new ItemDTO(2L, "apple", ItemStatus.READY));

    @Before
    public void init() {
        controller = new ItemController(itemService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(itemService.getItems(1, 10)).thenReturn(items);
    }

    @Test
    public void shouldReturnAllItems() {
        Model model = new ExtendedModelMap();
        String items = controller.showItems(model);
        assertThat(items, equalTo("items"));
        assertThat(model.asMap(), hasEntry("items", this.items));
    }

    @Test
    public void requestForItemsIsSuccessfullyProcessedWithAvailableItemsList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/items.html"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("items", equalTo(items)))
                .andExpect(forwardedUrl("items"));
    }

    @Test
    public void requestWasRedirectedToItemsPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/index"))
                .andExpect(status().isFound());
    }

    @Test
    public void requestWasRedirectedToItemPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/item.html"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("item"));
    }

    @Test
    public void requestPostWasRedirectedToItemPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/item.html"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("item"));
    }

    @Test
    public void requestPostUpdateItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/update"))
                .andExpect(status().isFound());
    }
}