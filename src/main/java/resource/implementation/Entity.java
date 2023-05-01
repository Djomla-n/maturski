package resource.implementation;

import lombok.Getter;
import lombok.Setter;
import resource.DBNode;
import resource.DBNodeComposite;

@Getter
@Setter
public class Entity extends DBNodeComposite {

    public Entity(String name, DBNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof Attribute){
            Attribute attribute = (Attribute) child;
            this.getChildren().add(attribute);
        }
    }//ako nije null i ako je child instanceof Attribute onda  pravi objekat klase Attribute i u njega dodeljuje child
    //koji pretvara u Attribute. Zatim u litu dodeljuje to dete

}
