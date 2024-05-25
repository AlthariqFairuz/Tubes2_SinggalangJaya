package com.resources.logic;

import com.resources.logic.plugin.JsonLoaderSaver;
import com.resources.logic.plugin.Plugin;
import com.resources.logic.plugin.PluginLoader;
import com.resources.logic.state.LoaderSaver;
import com.resources.logic.state.TextLoaderSaver;
import com.resources.logic.plugin.XMLLoaderSaver;

public class Main {
    public static void main(String[] args) {
        // Intiialize game logic

        // // Test
        // TextLoaderSaver textLoader = new TextLoaderSaver();
        // textLoader.loadState("state/state.txt");
        // System.out.println("===================================");
        // textLoader.saveState("state/new-state.txt");

        // // Test
        // LoaderSaver jsonLoader = new JsonLoaderSaver();
        // jsonLoader.loadState("state/state.json");
        // System.out.println("===================================");
        // jsonLoader.saveState("state/new-state.json");

        // // Test
        // LoaderSaver xmPlugin = new XMLLoaderSaver();
        // xmPlugin.loadState("state/state.xml");
        // xmPlugin.saveState("state/new-state.xml");

        // Test plugin json
        PluginLoader plugin = new PluginLoader();
        Plugin loaded = plugin.loadPlugin("jar/json.jar");
        System.out.println(loaded.getPluginType());
        loaded.loadState("state/state.json");
        loaded.saveState("state/new-state.json");

        // // Test plugin xml
        // PluginLoader plugin = new PluginLoader();
        // Plugin loaded = plugin.loadPlugin("jar/xml.jar");
        // System.out.println(loaded.getPluginType());
        // loaded.loadState("state/state.xml");
        // loaded.saveState("state/new-state.xml");
    }
}
