package com.manakov.model.converters;

import com.manakov.model.colorModel.LAB;
import com.manakov.model.colorModel.RGB;
import com.manakov.model.colorModel.XYZ;

public class RGB_To_LAB_Converter {

    public LAB convert_RGB_To_LAB(RGB rgb){
        return convert_XYZ_To_LAB(
                convert_RGB_To_XYZ(
                        rgb
                )
        );
    }

    private XYZ convert_RGB_To_XYZ(RGB rgb){
        double r = transformRGB(rgb.red / 255.0);
        double g = transformRGB(rgb.green / 255.0);
        double b = transformRGB(rgb.blue / 255.0);

        return new XYZ(
                r * 0.4124 + g * 0.3576 + b * 0.1805,
                r * 0.2126 + g * 0.7152 + b * 0.0722,
                r * 0.0193 + g * 0.1192 + b * 0.9505
        );
    }

    private double transformRGB(double n){
        return (n > 0.04045 ? Math.pow( (n + 0.055) / 1.055, 2.4) : n / 12.92) * 100;
    }

    private LAB convert_XYZ_To_LAB(XYZ xyz){
        double rX = 95.047;
        double rY = 100.00;
        double rZ = 108.883;

        double x = transformXYZ(xyz.x / rX );
        double y = transformXYZ(xyz.y / rY );
        double z = transformXYZ(xyz.z / rZ );

        return new LAB(116 * y - 16, 500*(x-y), 200*(y-z));
    }

    private double transformXYZ(double n){
        double i = Math.cbrt(n);
        return n > 0.008856 ? i : 7.787 * n + 16 / 116;
    }
}
