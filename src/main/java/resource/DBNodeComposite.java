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

    private List<DBNode> children;

    public DBNodeComposite(String name, DBNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();//pravi listu childrena
    }

    public DBNodeComposite(String name, DBNode parent, ArrayList<DBNode> children) {
        super(name, parent);
        this.children = children;
    }//ne koristi se


    public abstract void addChild(DBNode child);

    public DBNode getChildByName(String name) {
        for (DBNode child: this.getChildren()) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }//ne koristi se

}
