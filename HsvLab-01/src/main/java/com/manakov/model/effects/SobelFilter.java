package com.manakov.model.effects;

import com.manakov.model.ImageDate;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SobelFilter extends MatrixEffect {

    private int border;
    private ImageDate imageDate;

    private int[][] SobelX = { {-1, -2, -1},
            { 0,  0,  0},
            { 1,  2,  1}};

    private int[][] SobelY = {  {-1,  0,  1},
            {-2,  0,  2},
            {-1,  0,  1}};

    public SobelFilter(int border, ImageDate imageDate) {
        this.border = border;
        this.imageDate = imageDate;
    }

    @Override
    public int[] applyFilter(BufferedImage source, int x, int y) {
        int[] channels = new int[3];

        if (x == 0 || y == 0 || x == source.getWidth()-1 || y == source.getHeight()-1) {
            Color col = new Color(source.getRGB(x,y));
            channels[0] = col.getRed();
            channels[1] = col.getGreen();
            channels[2] = col.getBlue();

            imageDate.direction[x][y] = -1;
            return channels;
        }

        int[] diff1 = applyMatrix(source, x, y, SobelX);
        int[] diff2 = applyMatrix(source, x, y, SobelY);

        int out = (int)Math.round(Math.sqrt(diff1[0]*diff1[0]+diff2[0]*diff2[0]));

        imageDate.direction[x][y] = fillUnMax(diff1[0], diff2[0]);
        imageDate.value[x][y] = out;


        channels[0] = (out/4 > border) ? 255 : 0;
        channels[1] = (out/4 > border) ? 255 : 0;
        channels[2] = (out/4 > border) ? 255 : 0;

        return channels;
    }

    private int fillUnMax(int d1, int d2){
        double res;
        if (d2 == 0) {
            if (d1 >=0) res = Math.PI/2; else res = -Math.PI/2;
        } else {
            res = Math.atan((double) d1/ (double) d2);
        }

        if (res <= Math.PI / 8 && res > -Math.PI/8) return 0;
        if (res <= 3 * Math.PI / 8 && res > Math.PI / 8) return 1;
        if (res >= -3 * Math.PI / 8 && res < - Math.PI/8) return 2;
        return 3;
    }
}

