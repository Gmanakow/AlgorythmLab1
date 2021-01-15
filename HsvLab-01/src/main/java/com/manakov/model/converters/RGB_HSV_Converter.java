package com.manakov.model.converters;

import com.manakov.model.colorModel.HSV;
import com.manakov.model.colorModel.RGB;

public class RGB_HSV_Converter extends Converter {
    public HSV convert (RGB rgb){

        float H, S, V;
        double var_r, var_g, var_b;
        double var_Min, var_Max, del_Max;
        double del_R, del_G, del_B;

        var_r = (rgb.red * 1.0 / 255);
        var_g = (rgb.green * 1.0 / 255);
        var_b = (rgb.blue * 1.0 / 255);

        var_Min = Math.min(Math.min(var_r,var_g), var_b);
        var_Max = Math.max(Math.max(var_r,var_g), var_b);
        del_Max = var_Max - var_Min;

        V = (float) var_Max;

        if (del_Max == 0){
            H = 0;
            S = 0;
        } else {
            S = (float) (del_Max / var_Max);

            del_R = (((var_Max - var_r)/6)+(del_Max/2))/del_Max;
            del_G = (((var_Max - var_g)/6)+(del_Max/2))/del_Max;
            del_B = (((var_Max - var_b)/6)+(del_Max/2))/del_Max;

            H = 0;

            if (var_r == var_Max) {
                H = (float) (del_B - del_G);
            } else if (var_g == var_Max) {
                H = (float) ((1.0/3) + del_R - del_B);
            } else if (var_b == var_Max) {
                H = (float) ((2.0/3) + del_G - del_R);
            }

            if (H < 0) H+= 1;
            if (H > 1) H-= 1;
        }

        return new HSV(H, S, V);
    }

    public RGB convert(HSV hsv){

        int r, g, b;
        int var_i;
        double var_h, var_1, var_2, var_3, var_r, var_g, var_b;

        if (hsv.saturation == 0){
            r = (int) Math.floor(hsv.value * 255);
            g = (int) Math.floor(hsv.value * 255);
            b = (int) Math.floor(hsv.value * 255);
        } else {
            var_h = hsv.hue * 6;
            if (var_h == 6) var_h = 0;
            var_i = (int) Math.floor(var_h);

            var_1 = hsv.value * (1 - hsv.saturation);
            var_2 = hsv.value * (1 - hsv.saturation * (var_h - var_i));
            var_3 = hsv.value * (1 - hsv.saturation * (1 - (var_h - var_i)));


            switch ( var_i) {
                case (0) :
                    var_r = hsv.value;
                    var_g = var_3;
                    var_b = var_1;
                    break;
                case (1) :
                    var_r = var_2;
                    var_g = hsv.value;
                    var_b = var_1;
                    break;
                case(2) :
                    var_r = var_1;
                    var_g = hsv.value;
                    var_b = var_3;
                    break;
                case(3) :
                    var_r = var_1;
                    var_g = var_2;
                    var_b = hsv.value;
                    break;
                case(4) :
                    var_r = var_3;
                    var_g = var_1;
                    var_b = hsv.value;
                    break;
                default:
                    var_r = hsv.value;
                    var_g = var_1;
                    var_b = var_2;
                    break;
            }
            r = (int) (var_r * 255);
            g = (int) (var_g * 255);
            b = (int) (var_b * 255);
        }

        return new RGB(r, g, b);
    }


}
