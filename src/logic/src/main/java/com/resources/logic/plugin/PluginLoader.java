package com.resources.logic.plugin;

// PluginLoader.java
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.lang.reflect.InvocationTargetException;

public class PluginLoader {
    // Load a plugin from a JAR file
    public Plugin loadPlugin(String jarFilePath) {

        URLClassLoader classLoader = null;
        JarFile jarFile = null;

        try {
            // Load the JAR file
            File file = new File(jarFilePath);

            // Get url of the JAR file
            URL url = file.toURI().toURL();

            classLoader = new URLClassLoader(new URL[] { url }, this.getClass().getClassLoader());

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
                    // Get class name
                    String className = entry.getName().replace("/", ".").replace(".class", "");

                    // Load the class
                    Class<?> pluginClass = classLoader.loadClass(className);

                    // Check if the class is a plugin
                    if (Plugin.class.isAssignableFrom(pluginClass)) {
                        // Create an instance of the plugin
                        Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();

                        // Call the onLoad method of the plugin
                        plugin.onLoad();

                        // Return the plugin
                        return plugin;
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

        return null;
    }
}
