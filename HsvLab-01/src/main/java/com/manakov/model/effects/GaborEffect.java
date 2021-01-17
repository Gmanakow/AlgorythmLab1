package com.manakov.model.effects;

import com.manakov.model.colorModel.HSV;
import com.manakov.model.colorModel.RGB;
import com.manakov.model.converters.RGB_HSV_Converter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class GaborEffect implements AnEffect{

    private double gamma = 0.2;
    private double lyamda = 2.0;
    private double teta = 0.45; // 0.90 1.35
    private double fi = 0;
    private double sigma = 0.56*lyamda;

    private RGB_HSV_Converter converter = new RGB_HSV_Converter();

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

        RGB rgb = summarize(source, x, y);

        channels[0] = rgb.red;
        channels[1] = rgb.green;
        channels[2] = rgb.blue;

        return channels;
    }

    public double getX(double x, double y, double teta){
        return ((x * Math.cos(teta)) + (y * Math.sin(teta)));
    }

    public double getY(double x, double y, double teta){
        return ((-x*Math.sin(teta)) + (y * Math.cos(teta)));
    }

    public double Gabor(double x, double y){

        double sqX = getX(x, y, teta);
        sqX = sqX * sqX;

        double sqY = getY(x, y, teta);
        sqY = sqY * sqY;

        sqY = sqY * gamma * gamma;

        double res = Math.exp((-1/2) * (sqX + sqY)/(sigma * sigma));
        res = res * Math.cos( (2*Math.PI / lyamda) * getX(x,y, teta) + fi);
        return res;

    }

    public RGB summarize(BufferedImage image, int x, int y){
        double sum = 0.0;
        HSV hsv;
        RGB rgb;
        for (int i =0; i< 5; i++){
            for (int j = 0; j< 5; j++){
                rgb = new RGB(image.getRGB(x-2+i, y-2+j));
                hsv = converter.convert(rgb);

                double curr = hsv.value * Gabor(i,j);
                sum = sum + curr;
            }
        }

        sum = sum;

        rgb = new RGB(image.getRGB(x,y));
        hsv = converter.convert(rgb);
        System.out.println(hsv.value);
        hsv.value = (float) sum;
        System.out.println(hsv.value);
        rgb = converter.convert(hsv);
        return rgb;
    }


}
