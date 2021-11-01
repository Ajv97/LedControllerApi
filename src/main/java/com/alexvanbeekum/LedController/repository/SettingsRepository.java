package com.alexvanbeekum.LedController.repository;

import com.alexvanbeekum.LedController.entity.Settings;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SettingsRepository extends CrudRepository<Settings, Integer> {

    @Modifying
    @Query("update Settings s set s.brightness = ?2, s.settings_updated = true where s.set_id = ?1")
    void updateBrightness(Integer set_id, Integer brightness);

    @Modifying
    @Query("update Settings s set s.lights_updated = true where s.set_id = ?1")
    void lightsUpdated(Integer set_id);

}
