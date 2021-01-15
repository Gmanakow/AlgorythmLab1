package com.manakov.file;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileUtils {
    private static String defaultPath = "C:\\Users\\PC\\Desktop\\учеба\\паоци\\пикчи";

    public static File getFile(JPanel parent){
        JFileChooser fileChooser = new JFileChooser(defaultPath);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            return file;
        }
        return null;
    }

    public static BufferedImage getImage(JPanel panel){
        try{
            return ImageIO.read(getFile(panel));
        } catch (IOException | IllegalArgumentException ex){
            System.out.println("err in reading");
        }
        return null;
    }
}
