package com.resources.logic.plugin;

import com.resources.logic.state.LoaderSaver;

public interface Plugin extends LoaderSaver {
    public String getPluginType();
}
