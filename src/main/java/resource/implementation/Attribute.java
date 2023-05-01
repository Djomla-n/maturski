package resource.implementation;

import lombok.Getter;
import lombok.Setter;
import resource.DBNode;
import resource.DBNodeComposite;
import resource.enums.AttributeType;

@Getter
@Setter
public class Attribute extends DBNodeComposite {


    private AttributeType attributeType;// sadrzi tip (da li je int, varchar...)
    private int length;
    private Attribute inRelationWith;

    public Attribute(String name, DBNode parent) {
        super(name, parent);
    }

    public Attribute(String name, DBNode parent, AttributeType attributeType, int length) {
        super(name, parent);
        this.attributeType = attributeType;
        this.length = length;
    }

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof AttributeConstraint){
            AttributeConstraint attributeConstraint = (AttributeConstraint) child;
            this.getChildren().add(attributeConstraint);
        }
    }//ako child nije null i ako je child tipa DBNode ili bilo koje klase koja extenduje klasu AttributeConstraint
    // pravi attributeConstraint u koji dodaje child koji se pretvara u AttributeConstraint koji sadrzi informacije
    // imenu, roditelju, o tome da li je primary_key ili sta vec?
    // zatim u listu getChildren dodaje attributeConstraint
}

