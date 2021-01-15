package com.manakov;


import com.manakov.controller.Controller;
import com.manakov.model.Model;
import com.manakov.view.MainFrame;
import com.manakov.view.View;

public class App
{
    public static void main(String[] args  ) {
        MainFrame mainFrame = new MainFrame();

        View view = new View();

        mainFrame.add(view);
        mainFrame.setView(view);

        Model model = new Model();
        mainFrame.registerView(model);

        Controller controller = new Controller();
        mainFrame.setController(controller);

        controller.setModel(model);
        controller.setView(view);

        mainFrame.setVisible(true);
    }

}
