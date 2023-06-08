package query.rules;

import java.sql.SQLException;

public class RuleFactory {
    private RuleAlias ruleAlias= new RuleAlias();
    private RuleColumn ruleColumn= new RuleColumn();
    private RuleForeignKey ruleForeignKey= new RuleForeignKey();
    private RuleTable ruleTable= new RuleTable();
    private RuleWhere ruleWhere= new RuleWhere();

    public boolean choseRule(String ime, String upit) throws SQLException {
        switch (ime){
            case "RuleTable": return ruleTable.check(upit);
            case "RuleColumn" : return ruleColumn.check(upit);
            case "RuleWhere": return ruleWhere.check(upit);
            default:return true;
        }
    }
}
