package tree.implementation;

import resource.DBNode;
import resource.DBNodeComposite;
import resource.implementation.InformationResource;
import tree.Tree;
import tree.TreeItem;

import javax.swing.tree.DefaultTreeModel;
import java.util.List;

public class TreeImplementation implements Tree {

    @Override
    public DefaultTreeModel generateTree(InformationResource informationResource) {

        TreeItem root = new TreeItem(informationResource, informationResource.getName());//pravi glavni cvor
        connectChildren(root);//prosledjujemo glavni cvor i pravimo mu decu
        return new DefaultTreeModel(root);//vraca celo stablo
    }


    private void connectChildren(TreeItem current){

        if (!(current.getDbNode() instanceof DBNodeComposite)) return;
        //ako je current tipa DBNodeComposite ili bilo koje druge klase koja nasledjuje tu klasu nemoj da vratis nista

        List<DBNode> children = ((DBNodeComposite) current.getDbNode()).getChildren();
        //pravi listu dece tako sto uzima current i njegjov DbNode (glavni cvor) pretvara u DBNodeComposite,
        //a zatim poziva metodu getChildren() koja vraca sve podcvorove tog glavnog cvora
        for (int i = 0; i<children.size();i++){
            TreeItem child = new TreeItem(children.get(i), children.get(i).getName());//pravi novi TreeItem za svaki podcvor
            current.insert(child,i);//u current ubacuje dete na i-toj poziciji (tako pravi 'prvi red cvorova')
            connectChildren(child);//opet pozivamo metodu da napravi podcvorove za dete
        }

    }

}
