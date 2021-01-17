package com.manakov.model.effects.Canny;

import com.manakov.model.ImageDate;
import com.manakov.model.effects.AnEffect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnMaxEffect implements AnEffect {

    private ImageDate imageDate;

    public UnMaxEffect(ImageDate imageDate){
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

        int direction = imageDate.direction[x][y];
        int max = -1;

        for (int i = 0; i < 3; i++){
            for (int j =0; j<3; j++){
                if (imageDate.direction[x-1+i][y-1+j] == direction && imageDate.value[x-1+i][y-1+j] > max){
                    max = imageDate.value[x-1+i][y-1+j];
                }
            }
        }

        if (imageDate.value[x][y] == max && source.getRGB(x,y) == Color.WHITE.getRGB()) {
            channels[0] = 255;
            channels[1] = 255;
            channels[2] = 255;
        } else {
            channels[0] = 0;
            channels[1] = 0;
            channels[2] = 0;
        }

        return channels;
    }
}
