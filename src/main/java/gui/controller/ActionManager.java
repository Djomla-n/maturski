package gui.controller;

import lombok.Getter;


@Getter
public class ActionManager {
        private RunAction runAction;
        private PrettyAction prettyAction;
        private ExportAction exportAction;
        public ActionManager(){
            runAction = new RunAction();
            prettyAction = new PrettyAction();
            exportAction = new ExportAction();
        }
}
