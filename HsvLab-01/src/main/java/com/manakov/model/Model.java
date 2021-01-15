package com.manakov.model;

import com.manakov.model.colorModel.HSV;
import com.manakov.model.colorModel.LAB;
import com.manakov.model.colorModel.RGB;
import com.manakov.model.converters.RGB_HSV_Converter;
import com.manakov.model.converters.RGB_To_LAB_Converter;

import java.awt.image.BufferedImage;
import java.util.concurrent.SubmissionPublisher;

public class Model extends SubmissionPublisher<ImageDate> {
    private ImageDate imageDate = null;
    private double[] massive = new double[117];
    private int counter = 0;

    public Model(){}

    public void loadNewImage(BufferedImage incomeImage){
        this.imageDate = new ImageDate(incomeImage);
        change();
    }

    public void change(){
        try {
            this.offer(
                    imageDate,
                    null
            );
        } catch (Exception e) {
            System.out.println("err on change + " + e.getMessage());
        }
    }

    public void getRGB(int x, int y){
        try {

            RGB rgb = imageDate.getRGB(x, y);
            HSV hsv = new RGB_HSV_Converter().convert(rgb);
            LAB lab = new RGB_To_LAB_Converter().convert_RGB_To_LAB(rgb);

            System.out.print("RGB " + rgb.red + " " + rgb.green + " " + rgb.blue + " " );
            System.out.print("HSV " + hsv.hue + " " + hsv.saturation + " " + hsv.value + " " );
            System.out.println("LAB " + lab.l + " " + lab.a + " " + lab.b );

        } catch (NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void fillHistogramData(){
        RGB_To_LAB_Converter converter = new RGB_To_LAB_Converter();

        for (int i = 0; i < massive.length; i++){
            massive[i] = 0;
        }

        for (int i = 0; i < imageDate.getWidth(); i++){
            for (int j =0; j< imageDate.getHeight(); j++){
                massive[(int) Math.floor(
                        converter.convert_RGB_To_LAB(
                                imageDate.getRGB(i,j)
                        ).l
                ) + 16]++;
                counter++;
            }
        }
        if (counter!= 0)
        for (int i = 0; i < massive.length; i++){
            System.out.println(i + " " +massive[i]);
        }
        System.out.println("succeess");
    }

    public void changeHSV(){

        HSV hsv = null;
        RGB rgb = null;
        RGB_HSV_Converter rgb_hsv_converter = new RGB_HSV_Converter();

        for (int i = 0; i< imageDate.getWidth(); i++){
            for (int j=0; j< imageDate.getHeight(); j++){

                rgb = imageDate.getRGB(i, j);
                hsv = rgb_hsv_converter.convert(rgb);
                hsv.hue = (float) 0.78;
                rgb = rgb_hsv_converter.convert(hsv);
                imageDate.setRGB(i,j, rgb);
            }
        }
    }



}
