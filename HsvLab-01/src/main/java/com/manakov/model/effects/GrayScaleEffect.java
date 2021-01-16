package com.manakov.model.effects;


import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayScaleEffect implements AnEffect {

    @Override
    public int[] applyFilter(BufferedImage source, int x, int y) {
        int[] channels = new int[3];
        Color color = new Color(source.getRGB(x, y));
        int value = (int)(0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());

        channels[0] = value;
        channels[1] = value;
        channels[2] = value;
        return channels;
    }
}
