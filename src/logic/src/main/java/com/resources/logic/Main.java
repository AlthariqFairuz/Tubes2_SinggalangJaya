package com.resources.logic;

import com.resources.logic.plugin.JsonLoader;
import com.resources.logic.state.LoaderSaver;
import com.resources.logic.state.TextLoaderSaver;
import com.resources.logic.plugin.XMLLoader;

public class Main {
    public static void main(String[] args) {
        // Intiialize game logic

        // Test
        TextLoaderSaver textLoader = new TextLoaderSaver();

        // textLoader.loadState("state/state.txt");
        // System.out.println("===================================");
        // textLoader.saveState("state/new-state.txt");

        // // Test
        // LoaderSaver jsonLoader = new JsonLoader();
        // jsonLoader.loadState("state/state.json");
        // System.out.println("===================================");
        // jsonLoader.saveState("state/new-state.json");

        // // Test
        LoaderSaver xmPlugin = new XMLLoader();
        xmPlugin.loadState("state/state.xml");
        xmPlugin.saveState("state/new-state.xml");
    }
}
