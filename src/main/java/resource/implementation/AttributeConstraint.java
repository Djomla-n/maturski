package resource.implementation;

import lombok.Getter;
import lombok.Setter;
import resource.DBNode;
import resource.enums.ConstraintType;

@Getter
@Setter
public class AttributeConstraint extends DBNode {

    private ConstraintType constraintType;//pravi privatnu promenljivu koja je tipa ConstraintType(iz enum fajla)
    // koja sadrzi podatke o tabeli da li je primarni, strani kljuc...

    public AttributeConstraint(String name, DBNode parent, ConstraintType constraintType) {
        super(name, parent);
        this.constraintType = constraintType;
    }

}
