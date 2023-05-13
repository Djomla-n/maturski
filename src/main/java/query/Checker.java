package query;

import query.rules.Rule;
import query.rules.RuleFactory;

import java.util.ArrayList;
import java.util.Stack;

public class Checker implements Rule {

    private Stack<Description> errors= new Stack<>();
    private RuleFactory ruleFactory= new RuleFactory();
    public Checker(){
    }

    @Override
    public boolean check() {
        for (Description x : Description.getRules()) {
            if (!ruleFactory.choseRule(x.getName())) {
                errors.add(x);
            }
        }
        if (errors.isEmpty())
            return true;
        else//ispisi greske na ekran
        {
            errors.clear();
            return false;
        }
    }

}
