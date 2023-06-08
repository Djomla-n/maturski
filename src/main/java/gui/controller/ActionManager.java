package gui.controller;

import lombok.Getter;


@Getter
public class ActionManager {
        private RunAction runAction;
        private PrettyAction prettyAction;
        private UndoAction undoAction;
        private RedoAction redoAction;
        public ActionManager(){
            runAction = new RunAction();
            prettyAction = new PrettyAction();
            undoAction = new UndoAction();
            redoAction = new RedoAction();
        }
}
