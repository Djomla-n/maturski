package resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class DBNode {

    private String name;
    @ToString.Exclude
    private DBNode parent;
//klasa sadrzi ime samog cvora kao i podatke o roditelju (samo ime ako roditelj nema roditelja, a ako ima sve ok se ne dodje do glavnog cvora)


    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof DBNode) {
            DBNode otherObj = (DBNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }//ako objekat nije null i ako je objekat tipa DBNode ili bilo koje klase koja extenduje klasu DBNOde
    //pravi otherObj koji je tipa DBNode i u njega dodeljuje obj koji pretvara u DBNode
    //vraca true ako je ime obj jednako imenu OtherObj, u suprotnom vraca false
}
