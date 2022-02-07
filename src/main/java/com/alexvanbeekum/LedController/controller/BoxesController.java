package com.alexvanbeekum.LedController.controller;


import com.alexvanbeekum.LedController.entity.Boxes;
import com.alexvanbeekum.LedController.payload.requests.ColorRequest;
import com.alexvanbeekum.LedController.payload.requests.CreateBoxRequest;
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

    @CrossOrigin
    @PostMapping(path = "/box")
    public @ResponseBody
    String addNewBox(@RequestBody CreateBoxRequest box) {
        Boxes b = new Boxes();
        b.setBox_id(box.getBoxId());
        b.setSet_id(box.getSetId());
        b.setLed_count(box.getLedCount());
        b.clearColor();
        bRepository.save(b);
        return "saved";
    }

    @CrossOrigin
    @GetMapping(path="/boxes/{set_id}")
    public @ResponseBody
    Iterable<Boxes> getAllBoxes(@PathVariable int set_id) {
        return bRepository.findAllSetId(set_id);
    }

    @CrossOrigin
    @Transactional
    @PutMapping(path="/boxes/{set_id}/{box_id}")
    public @ResponseBody
    String updateBox(@PathVariable int set_id,
                     @PathVariable int box_id,
                     @RequestBody ColorRequest color){
        if(color.isR() && color.isG() && color.isB()){
            bRepository.updateBox(set_id, box_id, color.getR(), color.getG(), color.getB());
        } else {
            if(color.isR()){
                bRepository.updateBoxR(set_id, box_id, color.getR());
            }
            if(color.isG()){
                bRepository.updateBoxR(set_id, box_id, color.getG());
            }
            if(color.isB()){
                bRepository.updateBoxR(set_id, box_id, color.getB());
            }
        }
        sRepository.lightsUpdated(set_id);
        return "updated";
    }
}
