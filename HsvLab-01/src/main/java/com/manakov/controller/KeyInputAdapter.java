package com.manakov.controller;

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
        }
    }
}
