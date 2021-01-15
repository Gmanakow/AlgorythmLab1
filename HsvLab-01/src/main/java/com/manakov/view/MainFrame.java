package com.manakov.view;

import com.manakov.Constance;
import com.manakov.controller.Controller;
import com.manakov.model.Model;

import javax.swing.*;

public class MainFrame extends JFrame {
    private Controller controller;
    private View view;

    public MainFrame(){

        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(false);

        this.setSize(Constance.INSTANCE.width, Constance.INSTANCE.height);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
    public void setView(View view) {this.view = view;}

    public void registerView(Model model){
        model.subscribe(view);
    }

}
