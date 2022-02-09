package com.alexvanbeekum.LedController.controller;


import com.alexvanbeekum.LedController.entity.Settings;
import com.alexvanbeekum.LedController.payload.requests.SettingsRequest;
import com.alexvanbeekum.LedController.payload.responses.MessageResponse;
import com.alexvanbeekum.LedController.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MessageResponse> addNewSettings(@RequestParam Integer set_id){
        Settings s = new Settings(set_id);
        settingsRepository.save(s);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(HttpStatus.OK, "New settings Added"));
    }

    @CrossOrigin
    @GetMapping(path = "/settings/{set_id}")
    public @ResponseBody
    Optional<Settings> getSetting(@PathVariable Integer set_id){
        return settingsRepository.findById(set_id);
    }

    @CrossOrigin
    @Transactional
    @PutMapping(path = "/settings/{set_id}/day/{brightness}")
    public ResponseEntity<MessageResponse> updateDayBrightness(@PathVariable Integer set_id, @PathVariable Integer brightness){
        String message = "Set " + set_id + " day brightness set to ";
        if(brightness>255){
            settingsRepository.updateDayBrightness(set_id,255);
            message += "100%";
        } else {
            settingsRepository.updateDayBrightness(set_id,brightness);
            message += (int)((brightness/255.0)*100) + "%";
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(HttpStatus.OK, message));
    }

    @CrossOrigin
    @Transactional
    @PutMapping(path = "/settings/{set_id}/night/{brightness}")
    public ResponseEntity<MessageResponse> updateNightBrightness(@PathVariable Integer set_id, @PathVariable Integer brightness){
        String message = "Set " + set_id + " night brightness set to ";
        if(brightness>255){
            settingsRepository.updateNightBrightness(set_id,255);
            message += "100%";
        } else {
            settingsRepository.updateNightBrightness(set_id,brightness);
            message += (int)((brightness/255.0)*100) + "%";
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(HttpStatus.OK, message));
    }

    @CrossOrigin
    @Transactional
    @PutMapping(path = "/settings/{set_id}/switch/{on_off}")
    public ResponseEntity<MessageResponse> updateOnOff(@PathVariable Integer set_id, @PathVariable Integer on_off) {
        settingsRepository.turnOnOff(set_id,(on_off == 1));
        String message;
        if(on_off == 1){
            message = "Set " + set_id + " turned on";
        } else {
            message = "Set " + set_id + " turned off";
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(HttpStatus.OK, message));
    }

    @CrossOrigin
    @Transactional
    @PutMapping(path = "/settings/settingsAck/{set_id}")
    public ResponseEntity<MessageResponse> ackSettingsUpdate(@PathVariable Integer set_id) {
        settingsRepository.settingsUpdateACK(set_id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(HttpStatus.OK, "Ack Received"));
    }

    @CrossOrigin
    @Transactional
    @PutMapping(path = "/settings/lightsAck/{set_id}")
    public ResponseEntity<MessageResponse> ackLightsUpdate(@PathVariable Integer set_id) {
        settingsRepository.lightsUpdateACK(set_id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(HttpStatus.OK, "Ack Received"));
    }
}
