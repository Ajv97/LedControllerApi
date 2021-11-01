package com.alexvanbeekum.LedController.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Boxes {
    @Id
    private Integer box_id;

    private Integer set_id;

    private Integer led_count;

    private Integer r;

    private Integer g;

    private Integer b;

    public Integer getBox_id() {
        return box_id;
    }

    public void setBox_id(Integer box_id) {
        this.box_id = box_id;
    }

    public Integer getSet_id() {
        return set_id;
    }

    public void setSet_id(Integer set_id) {
        this.set_id = set_id;
    }

    public Integer getLed_count() {
        return led_count;
    }

    public void setLed_count(Integer led_count) {
        this.led_count = led_count;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public void clearColor(){
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }
}
