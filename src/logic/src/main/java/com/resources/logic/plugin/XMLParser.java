package com.resources.logic.plugin;

import java.lang.System;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;

import com.resources.logic.plugin.json.*;
import com.resources.logic.Game;
import com.resources.logic.Player;
import com.resources.logic.ShopItem;

public class XMLParser implements Plugin {
    
   public void onLoad() {
       // TODO Auto-generated method stub
       System.out.println("XMLParser loaded");
    }
    
    public void saveState(Game state, String filePath) {
        // TODO Auto-generated method stub
        System.out.println("XMLParser saveState");
        
    }  
    public void loadState(String filePath) {
          try {
            // Create ObjectMapper for XML
            XmlMapper xmlMapper = new XmlMapper();

            // Parse XML content into Java object
            File xmlFile = new File(filePath); // Replace with your XML file path
            
            Game gameInstance = xmlMapper.readValue(xmlFile, Game.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
