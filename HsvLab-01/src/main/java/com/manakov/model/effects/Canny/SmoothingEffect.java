package com.manakov.model.effects.Canny;

import com.manakov.model.effects.MatrixEffect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SmoothingEffect extends MatrixEffect {

    private int[][] smoothing = {
                    {2, 4, 5, 4, 2},
                    {4, 9, 12, 9, 4},
                    {5, 12, 15, 12, 5},
                    {4, 9, 12, 9, 4},
                    {2, 4, 5, 4, 2}
            };

    @Override
    public int[] applyFilter(BufferedImage source, int x, int y) {
        int[] channels = new int[3];

        if (x == 0 || y == 0 || x == 1 || y == 1 ||
                x == source.getWidth()-2 || y == source.getHeight()-2
                || x == source.getWidth()-1 || y == source.getHeight()-1) {
            Color col = new Color(source.getRGB(x,y));
            channels[0] = col.getRed();
            channels[1] = col.getGreen();
            channels[2] = col.getBlue();
            return channels;
        }

        channels = applyMatrix(source, x, y, smoothing);

        for (int i = 0; i< 3; i++){
            channels[i] = channels[i] / 159;
        }

        return channels;
    }
}
