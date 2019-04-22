package com.gmail.erofeev.st.alexei.thirdweek.controller.impl;

import com.gmail.erofeev.st.alexei.thirdweek.repository.enums.ItemStatus;
import com.gmail.erofeev.st.alexei.thirdweek.service.ItemService;
import com.gmail.erofeev.st.alexei.thirdweek.service.model.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

import static java.util.Arrays.asList;

@Controller
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;
    private final List<ItemStatus> enumItemStatuses = asList(ItemStatus.values());

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = {"/", "/index"})
    public String redirect() {
        return "redirect:/items";
    }

    @GetMapping("/item")
    public String save(Model model, ItemDTO itemDTO) {
        model.addAttribute("itemDTO", itemDTO);
        return "item";
    }

    @PostMapping("/item")
    public String save(@Valid ItemDTO itemDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "item";
        }
        ItemDTO saveItem = itemService.add(itemDTO);
        String message = saveItem.toString() + " was saved";
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("message", message);
        logger.debug(message);
        return "item";
    }

    @GetMapping("/items")
    public String showItems(Model model) {
        List<ItemDTO> items = itemService.getItems(1, 10);
        ItemDTO updatedItem = new ItemDTO();
        model.addAttribute("items", items);
        model.addAttribute("updatedItem", updatedItem);
        model.addAttribute("enumStatus", enumItemStatuses);
        return "items";
    }

    @PostMapping("/update")
    public String update(ItemDTO itemDTO) {
        Long id = itemDTO.getId();
        ItemStatus itemStatus = itemDTO.getItemStatus();
        itemService.update(id, itemStatus);
        return "redirect:/items";
    }

}
