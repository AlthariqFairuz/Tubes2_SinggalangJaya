package com.resources.logic;

import com.resources.logic.plugin.JsonLoader;
import com.resources.logic.plugin.Plugin;
import com.resources.logic.plugin.XMLLoader;

public class Main {
    public static void main(String[] args) {
        // Intiialize game logic
        Game game = Game.getInstance();

        // // Test
        Plugin jsonLoader = new JsonLoader();
        jsonLoader.loadState("state/state.json");
        jsonLoader.saveState("state/new-state.json");

        // // Test
        // Plugin xmPlugin = new XMLLoader();
        // xmPlugin.loadState("state/new-state.xml");
        // xmPlugin.saveState("state/new-state.xml");
    }
}
