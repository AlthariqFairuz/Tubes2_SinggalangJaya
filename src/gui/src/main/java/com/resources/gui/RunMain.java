package com.resources.gui;

import com.resources.logic.Game;

public class RunMain {
    public static void main(String[] args) {
        Game.getInstance();
        Game.getInstance().seed();

        MainGUI.main(args);
    }
}
