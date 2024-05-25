package com.resources.logic.plugin;

// PluginLoader.java
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.lang.reflect.InvocationTargetException;

// Singleton
public class PluginLoader {
    // Single instance
    private static final PluginLoader instance = new PluginLoader();

    // Use Hashmap for plugin key=plugin_type, value=plugin
    private List<Plugin> plugins;

    // Constructor
    private PluginLoader() {
        plugins = new ArrayList<>();
    }

    // Get the single instance
    public static PluginLoader getInstance() {
        return instance;
    }

    // Get all plugins options
    public List<String> getPlugins() {
        List<String> pluginNames = new ArrayList<>();
        for (Plugin plugin : plugins) {
            pluginNames.add(plugin.getPluginType());
        }
        return pluginNames;
    }

    // Get a plugin by its type
    public boolean isPluginExist(String pluginType) {
        for (Plugin plugin : plugins) {
            if (plugin.getPluginType().equals(pluginType)) {
                return true;
            }
        }
        return false;
    }

    // Add a plugin
    public void addPlugin(Plugin newPlugin) {
        if (!isPluginExist(newPlugin.getPluginType())) {
            plugins.add(newPlugin);
        }
    }

    // Get a plugin
    public Plugin getPlugin(String pluginType) {
        for (Plugin plugin : plugins) {
            if (plugin.getPluginType().equals(pluginType)) {
                return plugin;
            }
        }
        return null;
    }

    // Load a plugin from a JAR file
    public void loadPlugin(String jarFilePath) {

        URLClassLoader classLoader = null;
        JarFile jarFile = null;

        try {
            // Load the JAR file
            File file = new File(jarFilePath);

            // Get url of the JAR file
            URL url = file.toURI().toURL();

            classLoader = new URLClassLoader(new URL[] { url }, getInstance().getClass().getClassLoader());

            // Open the JAR file
            jarFile = new JarFile(file);
            // Create a class loader for the JAR file
            // Jarfiles contains classes that are not in the classpath

            // Iterate through the entries in the JAR file
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                // Get next entry in the JAR file
                JarEntry entry = entries.nextElement();

                // Check if the entry is a class file
                if (entry.getName().endsWith(".class")) {
                    System.out.println("Found class file: " + entry.getName());
                    // Get class name
                    // src.logic.targetes.com.resources.logic.plugin.JsonLoader
                    // get com.resources.logic.plugin.JsonLoader
                    String parsedClassName = entry.getName().replace("/", ".").replace(".class", "");
                    int comIdx = parsedClassName.indexOf("com.", 0);
                    String className = parsedClassName.substring(comIdx);
                    // DEBUG
                    System.out.println(className);

                    // Load the class
                    Class<?> pluginClass = classLoader.loadClass(className);

                    // Check if the class is a plugin
                    if (Plugin.class.isAssignableFrom(pluginClass)) {
                        System.out.println("Found plugin class: " + pluginClass.getName());

                        // Create an instance of the plugin
                        Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();

                        // Call the onLoad method of the plugin
                        plugin.onLoad();

                        // Add the plugin to the list of plugins
                        // System.out.println(plugin.getPluginType());
                        addPlugin(plugin);
                        // System.out.println(plugin.getPluginType());
                    }
                }
            }

        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                if (jarFile != null) {
                    jarFile.close();
                }
                if (classLoader != null) {
                    classLoader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
