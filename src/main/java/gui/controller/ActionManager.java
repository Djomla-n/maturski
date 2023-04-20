package gui.controller;

import lombok.Getter;

import javax.swing.*;
public class ActionManager {
        private oboj oboj;
        public ActionManager(){
            pokreniAkciju();
        }
        public void pokreniAkciju(){
            oboj= new oboj();
        }
}
