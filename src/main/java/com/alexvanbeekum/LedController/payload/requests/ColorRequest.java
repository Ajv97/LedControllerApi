package com.alexvanbeekum.LedController.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ColorRequest {
    private Integer r;
    private Integer g;
    private Integer b;

    public boolean isR(){
        return r!=null;
    }

    public boolean isG(){
        return g!=null;
    }

    public boolean isB(){
        return b!=null;
    }
}
