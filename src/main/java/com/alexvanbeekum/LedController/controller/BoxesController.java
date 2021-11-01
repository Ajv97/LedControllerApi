package com.alexvanbeekum.LedController.controller;


import com.alexvanbeekum.LedController.entity.Boxes;
import com.alexvanbeekum.LedController.repository.BoxesRepository;
import com.alexvanbeekum.LedController.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@RequestMapping(path = "/api")      // this is the start of the path
public class BoxesController {
    @Autowired
    private BoxesRepository bRepository;
    @Autowired
    private SettingsRepository sRepository;

    @PostMapping(path = "/box")
    public @ResponseBody
    String addNewBox(@RequestParam Integer box_id, @RequestParam Integer set_id, @RequestParam Integer led_count) {
        Boxes b = new Boxes();
        b.setBox_id(box_id);
        b.setSet_id(set_id);
        b.setLed_count(led_count);
        b.clearColor();
        bRepository.save(b);
        return "saved";
    }

    @GetMapping(path="/boxes/{set_id}")
    public @ResponseBody
    Iterable<Boxes> getAllBoxes(@PathVariable int set_id) {
        System.out.println(set_id);
        return bRepository.findAllSetId(set_id);
    }

    @Transactional
    @PutMapping(path="/boxes/{set_id}/{box_id}")
    public @ResponseBody
    String updateBox(@PathVariable int set_id,
                     @PathVariable int box_id,
                     @RequestParam int r, @RequestParam int g, @RequestParam int b){
        bRepository.updateBox(set_id, box_id, r, g, b);
        sRepository.lightsUpdated(set_id);
        return "updated";
    }
}
