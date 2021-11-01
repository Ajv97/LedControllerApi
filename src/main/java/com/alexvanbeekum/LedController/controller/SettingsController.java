package com.alexvanbeekum.LedController.controller;


import com.alexvanbeekum.LedController.entity.Settings;
import com.alexvanbeekum.LedController.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class SettingsController {
    @Autowired
    private SettingsRepository settingsRepository;

    @PostMapping(path = "/settings")
    public @ResponseBody
    String addNewSettings(@RequestParam Integer set_id){
        Settings s = new Settings(set_id);
        settingsRepository.save(s);
        return "saved";
    }

    @GetMapping(path = "/settings/{set_id}")
    public @ResponseBody
    Optional<Settings> getSetting(@PathVariable Integer set_id){
        return settingsRepository.findById(set_id);
    }

    @Transactional
    @PutMapping(path = "/settings/{set_id}")
    public @ResponseBody
    String updateBrightness(@PathVariable Integer set_id, @RequestParam Integer brightness){
        if(brightness>255){
            brightness = 255;
        }
        settingsRepository.updateBrightness(set_id,brightness);
        return "updated";
    }
}
