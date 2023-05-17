package query.rules;

public class RuleFactory {
    private RuleAlias ruleAlias= new RuleAlias();
    private RuleColumn ruleColumn= new RuleColumn();
    private RuleForeignKey ruleForeignKey= new RuleForeignKey();
    private RuleGroupBy ruleGroupBy= new RuleGroupBy();
    private RuleTable ruleTable= new RuleTable();
    private RuleWhere ruleWhere= new RuleWhere();

    public boolean choseRule(String ime, String [] upit){
        switch (ime){
            case "RuleAlias": return ruleAlias.check(upit);
            case "RuleColumn" : return ruleColumn.check(upit);
            case "RuleForeignKey": return ruleForeignKey.check(upit);
            case "RuleGroupBy": return ruleGroupBy.check(upit);
            case "RuleTable": return ruleTable.check(upit);
            case "RuleWhere": return ruleWhere.check(upit);
            default:return true;
        }
    }
}
