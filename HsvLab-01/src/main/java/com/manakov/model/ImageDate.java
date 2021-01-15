package com.manakov.model;

import com.manakov.model.colorModel.RGB;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageDate {
    private BufferedImage image;

    public ImageDate(BufferedImage image){
        this.image = image;
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public RGB getRGB(int x, int y) throws ArrayIndexOutOfBoundsException{
        Color color = new Color(image.getRGB (x, y));
        return new RGB(color.getRed(), color.getGreen(), color.getBlue());
    }

    public int getWidth(){
        return this.image.getWidth();
    }

    public int getHeight(){
        return this.image.getHeight();
    }

    public void setRGB(int x, int y, RGB rgb){
        Color color = new Color(rgb.red, rgb.green, rgb.blue);
        this.image.setRGB(x,y, color.getRGB());
    }
}
