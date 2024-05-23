package com.resources.logic.plugin;

import com.resources.logic.Game;

public interface Plugin {
    void onLoad();

    void saveState(Game state, String filePath);

    void loadState(String filePath);
}