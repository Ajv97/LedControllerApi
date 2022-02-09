package com.alexvanbeekum.LedController.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Settings {
    @Id
    private Integer set_id;

    private boolean lights_updated;

    private boolean settings_updated;

    private Integer brightness;

    private Integer day_brightness;

    private Integer night_brightness;

    private boolean on_off;

    public Settings() {}

    public Settings(Integer set_id) {
        this.set_id = set_id;
        this.lights_updated = false;
        this.settings_updated = false;
        this.brightness = 0;
    }

    public Integer getSet_id() {
        return set_id;
    }

    public void setSet_id(Integer set_id) {
        this.set_id = set_id;
    }

    public boolean isLights_updated() {
        return lights_updated;
    }

    public void setLights_updated(boolean lights_updated) {
        this.lights_updated = lights_updated;
    }

    public boolean isSettings_updated() {
        return settings_updated;
    }

    public void setSettings_updated(boolean settings_updated) {
        this.settings_updated = settings_updated;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Integer getDay_brightness() {
        return day_brightness;
    }

    public void setDay_brightness(Integer day_brightness) {
        this.day_brightness = day_brightness;
    }

    public Integer getNight_brightness() {
        return night_brightness;
    }

    public void setNight_brightness(Integer night_brightness) {
        this.night_brightness = night_brightness;
    }

    public boolean isOn_off() {
        return on_off;
    }

    public void setOn_off(boolean on_off) {
        this.on_off = on_off;
    }
}
