package resource.implementation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import resource.DBNode;
import resource.DBNodeComposite;


@Getter
@Setter
@ToString(callSuper = true)
public class InformationResource extends DBNodeComposite {


    public InformationResource(String name) {
        super(name, null);
    }
    //poziva konstruktore name i parent iz klase DBNodeComposite, s tim sto ime se dodeljuje prilikom
    // pravljenja objekta ove klase, a parent se automatski stavlja na null jer ovo je glavni cvor (nema roditelja)

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof Entity){
            Entity entity = (Entity) child;
            this.getChildren().add(entity);
        }
    }//ako nije null i kao je instanceof Entity pravi entity u koji dodeljuje child koji pretvara u Entity,
    //zatim u listu getChildren dodaje taj entity koji je napravljen
}
