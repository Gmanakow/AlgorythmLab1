package com.manakov.model.effects.Canny;

import com.manakov.model.colorModel.RGB;
import com.manakov.model.converters.RGB_HSV_Converter;
import com.manakov.model.effects.AnEffect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OtsuEffect implements AnEffect {

    private RGB_HSV_Converter converter = new RGB_HSV_Converter();
    private BufferedImage bufferedImage = null;
    private int threshHold = 0;

    public OtsuEffect(BufferedImage source){
        this.bufferedImage = source;
        OtsuThreshhold();
        System.out.println(threshHold);
    }

    @Override
    public int[] applyFilter(BufferedImage source, int x, int y) {
        int[] channels = new int[3];

        int val = (int) Math.floor(converter.convert(new RGB(bufferedImage.getRGB(x, y))).value*1000);

        channels[0] = (val > threshHold) ? 255 : 0;
        channels[1] = (val > threshHold) ? 255 : 0;
        channels[2] = (val > threshHold) ? 255 : 0;

        return channels;
    }

    public void  OtsuThreshhold(){
        int val =(int) Math.floor(converter.convert(new RGB(bufferedImage.getRGB(0, 0))).value*1000);

        int min = val;
        int max = val;

        System.out.println(min + " " + max);

        for (int i = 0; i< bufferedImage.getWidth(); i++){
            for (int j =0; j< bufferedImage.getHeight(); j++){
                val = (int) Math.floor(converter.convert(new RGB(bufferedImage.getRGB(i, j))).value*1000);
                if (val > max) max = val;
                if (val < min) min = val;
            }
        }



        int[] histogram = new int[max - min + 1];

        for (int i = 0; i< histogram.length; i++){
            histogram[i] = 0;
        }

        for (int i = 0; i< bufferedImage.getWidth(); i++){
            for (int j =0; j< bufferedImage.getHeight(); j++){
                val = (int) Math.floor(converter.convert(new RGB(bufferedImage.getRGB(i, j))).value*1000);

                histogram[val - min]++;
            }
        }

        int m = 0;
        int n = 0;

        for (int i = 0; i< max -min; i++){
            m+= i * histogram[i];
            n+= histogram[i];
        }

        float maxSigma = -1;
        int threshHold = 0;

        int alpha1 = 0;
        int beta1 = 0;

        for (int i = 0; i< max - min; i++){
            alpha1 += i * histogram[i];
            beta1 += histogram[i];

            float w1 = (float) beta1 / n;

            float a = (float) alpha1 / beta1 - (float)(m - alpha1) / (n - beta1);

            float sigma = w1* (1-w1)*a*a;

            if (sigma > maxSigma){
                maxSigma = sigma;
                threshHold = i;
            }
        }

        threshHold += min;
        this.threshHold = threshHold;
    }
}
