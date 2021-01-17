package com.manakov.model.colorModel;

import java.awt.*;
import java.awt.image.ColorConvertOp;

public class RGB {
    public int red;
    public int green;
    public int blue;

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public boolean isIdentical(RGB rgb){
        return (this.red == rgb.red && this.green == rgb.green && this.blue == rgb.blue);
    }

    public RGB (int input){
        Color color = new Color(input);
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }
}
