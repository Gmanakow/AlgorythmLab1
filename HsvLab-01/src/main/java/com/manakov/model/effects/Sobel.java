package com.manakov.model.effects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sobel extends MatrixEffect {

    private int border;

    private int[][] SobelX = { {-1, -2, -1},
            { 0,  0,  0},
            { 1,  2,  1}};

    private int[][] SobelY = {  {-1,  0,  1},
            {-2,  0,  2},
            {-1,  0,  1}};

    public Sobel(int border) {
        this.border = border;
    }

    @Override
    public int[] applyFilter(BufferedImage source, int x, int y) {
        int[] channels = new int[3];

        if (x == 0 || y == 0 || x == source.getWidth()-1 || y == source.getHeight()-1) {
            Color col = new Color(source.getRGB(x,y));
            channels[0] = col.getRed();
            channels[1] = col.getGreen();
            channels[2] = col.getBlue();
            return channels;
        }

        int[] diff1 = applyMatrix(source, x, y, SobelX);
        int[] diff2 = applyMatrix(source, x, y, SobelY);
        int out = (int)Math.round(Math.sqrt(diff1[0]*diff1[0]+diff2[0]*diff2[0]));

        channels[0] = (out/4 > border) ? 255 : 0;
        channels[1] = (out/4 > border) ? 255 : 0;
        channels[2] = (out/4 > border) ? 255 : 0;

        return channels;
    }
}

