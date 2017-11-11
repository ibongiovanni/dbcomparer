package utils;

import descriptor.ForeignKey;

 /*
 * Uso esta clase para almacenar temporalmente informacion de las FK's.
 * Esto se debe a que mientras creo una tabla con sus respectivas fk's las tablas a las que referencian las mismas
 * muy posiblemente aun no fueron obtenidas. 
 */

public class Tuple {
    private String first; //first member of pair
    private String second; //second member of pair
    private ForeignKey third;
    
    public Tuple(String first, String second, ForeignKey third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }
    
    public void setThird(ForeignKey third) {
        this.third = third;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
    
    public ForeignKey getThird() {
        return third;
    }
}