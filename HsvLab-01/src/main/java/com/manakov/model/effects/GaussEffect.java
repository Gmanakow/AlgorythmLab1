package com.manakov.model.effects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GaussEffect implements AnEffect{

    double sigma = 1.4;

    double[][] gaussKernel = new double[5][5];

    public GaussEffect(){
        fillKernel();
    }

    @Override
    public int[] applyFilter(BufferedImage source, int x, int y) {
        int[] channels = new int[3];

        if (x == 0 || y == 0 || x == 1 || y == 1 ||
                x == source.getWidth()-1 || y == source.getHeight()-1 ||
                x == source.getWidth()-2 || y == source.getHeight()-2) {
            Color col = new Color(source.getRGB(x,y));
            channels[0] = col.getRed();
            channels[1] = col.getGreen();
            channels[2] = col.getBlue();
            return channels;
        }

        double[] out = new double[3];
        out[0] = 0.0;
        out[1] = 0.0;
        out[2] = 0.0;

        for (int i =0; i< 5; i++){
            for (int j = 0; j< 5; j++){
                Color color = new Color(source.getRGB(x - 2 + i, y - 2 + j));
                out[0] += color.getRed() * gaussKernel[i][j];
                out[1] += color.getGreen() * gaussKernel[i][j];
                out[2] += color.getBlue() * gaussKernel[i][j];
            }
        }

        channels[0] = (int) Math.floor(out[0]);
        channels[1] = (int) Math.floor(out[1]);
        channels[2] = (int) Math.floor(out[2]);

        return channels;
    }

    public double Gauss(double x, double y){
        double res = 0.0;
        res = 1 / (2 * Math.PI * sigma * sigma);
        res = res * Math.exp( - (x * x + y * y) / (2 * sigma * sigma));
        return res;
    }

    public void fillKernel(){
        for (int i = -2; i< 3; i++){
            for (int j = -2; j< 3; j++){
                gaussKernel[i+2][j+2] = Gauss(i,j);
            }
        }
    }

}
