package com.manakov.controller;

import com.manakov.model.effects.Canny.OtsuEffect;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputAdapter extends KeyAdapter {

    private Controller controller = null;

    public KeyInputAdapter(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        switch (e.getKeyCode()){
            case (KeyEvent.VK_SPACE) :
                controller.changeHSV();
                break;
            case (KeyEvent.VK_H) :
                controller.showHistogram();
                break;
            case (KeyEvent.VK_S) :
                controller.applyEffect(0);
                break;
            case (KeyEvent.VK_G) :
                controller.applyEffect(1);
                break;
            case (KeyEvent.VK_C) :
                controller.applyEffect(2);
                break;

            case (KeyEvent.VK_Z) : // Gauss
                controller.applyEffect(3);
                break;

            case (KeyEvent.VK_O) :
                controller.applyEffect(4);
                break;

        }
    }
}
