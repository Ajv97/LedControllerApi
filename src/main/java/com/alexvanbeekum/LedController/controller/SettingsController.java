package com.alexvanbeekum.LedController.controller;


import com.alexvanbeekum.LedController.entity.Settings;
import com.alexvanbeekum.LedController.payload.requests.SettingsRequest;
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

    @CrossOrigin
    @PostMapping(path = "/settings")
    public @ResponseBody
    String addNewSettings(@RequestParam Integer set_id){
        Settings s = new Settings(set_id);
        settingsRepository.save(s);
        return "saved";
    }

    @CrossOrigin
    @GetMapping(path = "/settings/{set_id}")
    public @ResponseBody
    Optional<Settings> getSetting(@PathVariable Integer set_id){
        return settingsRepository.findById(set_id);
    }

    @CrossOrigin
    @Transactional
    @PutMapping(path = "/settings/{set_id}/brightness/{brightness}")
    public @ResponseBody
    String updateBrightness(@PathVariable Integer set_id, @PathVariable Integer brightness){
        if(brightness>255){
            settingsRepository.updateBrightness(set_id,255);
        } else {
            settingsRepository.updateBrightness(set_id,brightness);
        }
        return "updated";
    }
}
