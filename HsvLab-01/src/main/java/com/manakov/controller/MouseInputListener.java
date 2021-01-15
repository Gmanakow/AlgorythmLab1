package com.manakov.controller;

import com.manakov.Constance;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseInputListener extends MouseInputAdapter {
    private Controller controller;

    public MouseInputListener(Controller controller){
        this.controller = controller;
        System.out.println("create");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        switch (e.getButton()){
            case (0):                                                       // Button1 to be exact, but smh it changed Σ(･¬･)
                controller.pixel(
                        e.getX() - Constance.INSTANCE.xOffset,
                        e.getY() - Constance.INSTANCE.yOffset
                );
                break;
        }
    }
}
