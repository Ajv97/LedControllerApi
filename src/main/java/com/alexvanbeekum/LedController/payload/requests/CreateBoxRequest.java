package com.alexvanbeekum.LedController.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateBoxRequest {
    @NotNull
    private int boxId;
    @NotNull
    private int setId;
    @NotNull
    private int ledCount;
}
