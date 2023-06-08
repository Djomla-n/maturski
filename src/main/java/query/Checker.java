package query;

import query.rules.Rule;
import query.rules.RuleFactory;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

public class Checker implements Rule {

    private Stack<Description> errors= new Stack<>();
    private RuleFactory ruleFactory= new RuleFactory();
    public Checker(){
    }

    @Override
    public boolean check(String upit) throws SQLException {
        for (Description x : Description.getRules()) {
            if (!ruleFactory.choseRule(x.getName(), upit)) {
                errors.add(x);
            }
        }
        if (errors.isEmpty())
            return true;
        else//ispisi greske na ekran
        {
            for(int i = 0;i<errors.size();i++) {
                JOptionPane.showMessageDialog(null, errors.get(i).getDesc() + "\n" + errors.get(i).getSugg());
            }
            errors.clear();
            return false;
        }
    }

}
