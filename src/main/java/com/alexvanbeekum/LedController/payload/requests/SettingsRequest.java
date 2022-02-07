package com.alexvanbeekum.LedController.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettingsRequest {
    private Integer brightness;
}
