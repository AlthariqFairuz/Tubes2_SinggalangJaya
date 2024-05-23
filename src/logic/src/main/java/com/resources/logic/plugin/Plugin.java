package com.resources.logic.plugin;

public interface Plugin {
    void onLoad();

    void saveState(String filePath);

    void loadState(String filePath);
}