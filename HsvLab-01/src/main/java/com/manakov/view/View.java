package com.manakov.view;

import com.manakov.Constance;
import com.manakov.file.FileUtils;
import com.manakov.model.ImageDate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Flow;

public class View extends JPanel implements Flow.Subscriber<ImageDate> {
    private Flow.Subscription subscription = null;
    private BufferedImage image = null;

    public View(){
        this.setFocusable(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.image != null) {
            g.drawImage(image, Constance.INSTANCE.xOffset, Constance.INSTANCE.yOffset, this);
        }
    }

    public BufferedImage getNewFile(){
        return FileUtils.getImage(this);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }
    @Override
    public void onNext(ImageDate imageDate) {
        this.image = imageDate.getImage();

        repaint();
        this.subscription.request(1);
    }
    @Override
    public void onError(Throwable throwable) {
        System.out.println("On Error + " + throwable.getMessage());
    }
    @Override
    public void onComplete() {
        System.out.println("On Complete");
    }
}