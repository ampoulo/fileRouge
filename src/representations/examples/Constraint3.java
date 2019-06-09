/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import representations.*;

/**
 *Aucune n'a les deux côtés noirs
 * @author nokenn
 */
public class Constraint3 implements Constraint{

    private final Variable droit;
    private final Variable gauche;
    private final HashMap<Variable, String> constrainte3;
    private final IncompatibilityConstraint incomp;
    
    public Constraint3(){
        
        this.constrainte3 = new HashMap();

        this.gauche = new Variable("gauche", new ArrayList<>());
        this.droit = new Variable("droit", new ArrayList<>());
        this.constrainte3.put(gauche, "noir");
        this.constrainte3.put(droit, "noir");
        this.incomp = new IncompatibilityConstraint(this.constrainte3); // la 3eme contrainte
    }
    @Override
    public Set<Variable> getScope(){
       return this.incomp.getScope();
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> choiclient) {
        return this.incomp.isSatisfiedBy(choiclient);
    }


    @Override
    public boolean filter(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
