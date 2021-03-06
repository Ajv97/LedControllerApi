package com.alexvanbeekum.LedController.repository;

import com.alexvanbeekum.LedController.entity.Settings;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SettingsRepository extends CrudRepository<Settings, Integer> {

    @Modifying
    @Query("update Settings s set s.day_brightness = ?2, s.settings_updated = true where s.set_id = ?1")
    void updateDayBrightness(Integer set_id, Integer brightness);

    @Modifying
    @Query("update Settings s set s.night_brightness = ?2, s.settings_updated = true where s.set_id = ?1")
    void updateNightBrightness(Integer set_id, Integer brightness);

    @Modifying
    @Query("update Settings s set s.on_off = ?2, s.settings_updated = true where s.set_id = ?1")
    void turnOnOff(Integer set_id, boolean on_off);

    @Modifying
    @Query("update Settings s set s.lights_updated = true where s.set_id = ?1")
    void lightsUpdated(Integer set_id);

    @Modifying
    @Query("update Settings s set s.lights_updated = false where s.set_id = ?1")
    void lightsUpdateACK(Integer set_id);

    @Modifying
    @Query("update Settings s set s.settings_updated = false where s.set_id = ?1")
    void settingsUpdateACK(Integer set_id);
}
