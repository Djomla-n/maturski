package gui.controller;

import lombok.Getter;

import javax.swing.*;
import java.net.URL;

@Getter
public abstract class AbstractMaturskiAction extends AbstractAction {
    public Icon loadIcon(String fileName){
        URL imageURL = ClassLoader.getSystemResource(fileName);
        Icon icon = null;

        if (imageURL != null){
            icon = new ImageIcon(imageURL);
        }
        else{
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }
}
