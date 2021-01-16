package com.manakov.controller;

import com.manakov.Constance;
import com.manakov.model.Model;
import com.manakov.view.View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller {

    private Model model;
    private View view;

    public void setModel(Model model){
        this.model = model;
    }
    public void setView(View view) {
        this.view = view;

        MouseInputListener mouseInputListener = new MouseInputListener(this);
        this.view.addMouseMotionListener(mouseInputListener);

        MouseInputAdapter mouseInputAdapter = new MouseInputAdapter(this);
        this.view.addMouseListener(mouseInputAdapter);

        KeyInputAdapter keyInputAdapter = new KeyInputAdapter(this);
        this.view.addKeyListener(keyInputAdapter);
    }

    public void pixel(int x, int y){
        this.model.getRGB(x,y);
    }

    public void load(){
        model.loadNewImage(
                view.getNewFile()
        );
    }

    public void changeHSV(){
        model.changeHSV();
        System.out.println("change");
    }

    public void applyEffect(int i){
        model.applyEffect(i);
        System.out.println("apply effect + " + i);
    }

    public void showHistogram(){
        model.fillHistogramData();
    }
}
