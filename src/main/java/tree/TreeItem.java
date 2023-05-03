package tree;

import lombok.Getter;
import lombok.Setter;
import resource.DBNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Iterator;

@Getter
@Setter
public class TreeItem<DBNode> extends DefaultMutableTreeNode {


    private String name;//ime
    private DBNode dbNode;//cvor

    public TreeItem(DBNode node) {
        this.dbNode = node;
    }

    public TreeItem(DBNode dbNode, String name) {
        this.name = name;
        this.dbNode = dbNode;
    }

    @Override
    public int getIndex(TreeNode node) {
        return findIndexByChild((TreeItem)node);
    }//vraca indeks zadatog podcvora

    @Override
    public TreeNode getChildAt(int childIndex) {
        return findChildByIndex(childIndex);
    }//vraca podcvor koji se nalazi na poziciji childIndex

    @Override
    public int getChildCount() {
        if(dbNode instanceof DBNodeComposite)
            return ((DBNodeComposite) dbNode).getChildren().size();
        return 0;
    }//vraca ukupan broj podcvorova

    @Override
    public boolean getAllowsChildren() {
        if(dbNode instanceof DBNodeComposite)
            return true;
        return false;
    }//true ako cvor koji je pozvao metodu moze da sadrzi podcvorove else u suprotnom

    @Override
    public boolean isLeaf() {
        if(dbNode instanceof DBNodeComposite)
            return false;
        return true;
    }//da li je pozivalac list

    @Override
    public Enumeration children() {
        if(dbNode instanceof DBNodeComposite)
            return (Enumeration) ((DBNodeComposite) dbNode).getChildren();
        return null;
    }//vraca sve podcvorove i pretvara ih u enum


    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof TreeItem) {
            TreeItem otherObj = (TreeItem) obj;
            return this.dbNode.equals(otherObj.dbNode);
        }
        return false;
    }//objasnjeno vise puta u resource.implementation

    private TreeNode findChildByIndex(int childIndex){

        if(dbNode instanceof DBNodeComposite){
            TreeItem<DBNode> toLookFor = new TreeItem (((DBNodeComposite) dbNode).getChildren().get(childIndex));

            Iterator childrenIterator = children.iterator();
            TreeNode current;

            while (childrenIterator.hasNext()){
                current = (TreeNode) childrenIterator.next();
                if (current.equals(toLookFor))
                    return current;
            }
        }

        return null;
    }//nalazi podcvor pozadatom indeksu

    private int findIndexByChild(TreeItem node){

        if(dbNode instanceof DBNodeComposite){
            return  ((DBNodeComposite) dbNode).getChildren().indexOf(node.getDbNode());
        }

        return -1;
    }//vraca indeks samog podcvora koji je prosledjen

    @Override
    public String toString() {
        return name;
    }//ispisuje ime podcvora
}
