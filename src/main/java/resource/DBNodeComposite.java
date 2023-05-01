package resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class DBNodeComposite extends DBNode{

    private List<DBNode> children;//lista dece

    public DBNodeComposite(String name, DBNode parent) {
        super(name, parent);//poziva konstruktor klase DBNode i dodeljuje ime i roditelja
        this.children = new ArrayList<>();
    }//primarni konstruktor

    public DBNodeComposite(String name, DBNode parent, ArrayList<DBNode> children) {
        super(name, parent);
        this.children = children;
    }//ne koristi se


    public abstract void addChild(DBNode child);//apstraktna metoda koja dodaje dete (zavisi od klase u kojoj je definisana)

    public DBNode getChildByName(String name) {
        for (DBNode child: this.getChildren()) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }//ne koristi se

}
