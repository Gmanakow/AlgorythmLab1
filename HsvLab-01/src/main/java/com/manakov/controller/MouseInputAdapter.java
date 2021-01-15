package com.manakov.controller;

import com.manakov.Constance;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInputAdapter extends MouseAdapter {
    private Controller controller;

    public MouseInputAdapter(Controller controller){
        this.controller = controller;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        switch (e.getButton()) {
            case (MouseEvent.BUTTON3) :
                controller.load();
                break;
            case (MouseEvent.BUTTON1) :
                controller.pixel(
                        e.getX() - Constance.INSTANCE.xOffset,
                        e.getY() - Constance.INSTANCE.yOffset
                );
                break;
        }
    }
}
