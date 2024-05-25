package com.resources.logic.state;

public interface LoaderSaver {
    void onLoad();

    void loadState(String filePath);

    void onSave();

    void saveState(String filePath);
}
