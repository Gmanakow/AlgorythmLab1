package com.manakov.model.effects;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MatrixEffect implements AnEffect {

    public int[] applyMatrix(BufferedImage source, int x, int y, int[][] kernel) {
        int[] out = new int[3];

        int offset = kernel.length/2;
        for (int i = x-offset; i <= x+offset; i++)
            for (int j = y-offset; j <= y+offset; j++) {
                Color col = new Color(source.getRGB(i, j));
                int kernelX = i+offset-x;
                int kernelY = j+offset-y;
                out[0] += kernel[kernelX][kernelY]*col.getRed();
                out[1] += kernel[kernelX][kernelY]*col.getGreen();
                out[2] += kernel[kernelX][kernelY]*col.getBlue();
            }

        return out;
    }

    public int[] applyFilter(BufferedImage source, int x, int y) {
        return null;
    }
}
