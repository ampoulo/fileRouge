/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations.examples;

import java.util.ArrayList;
import java.util.Map;
import representations.*;

/**
 *
 * @author nokenn
 */
public class ConstraintDisjunction {

    private final ArrayList<Constraint> one;

    public ConstraintDisjunction() {

        this.one = new ArrayList<>();
    }

    public void ajouter(Constraint one) {

        this.one.add(one);
    }

    public void remove(Constraint one) {

        this.one.remove(one);
    }

    public boolean isSatisfiedBy(Map<Variable, String> choiclient) {

        boolean retour = false;

        for (int i = 0; i < this.one.size(); i++) {

            retour = retour || this.one.get(i).isSatisfiedBy(choiclient);
        }

        return retour;
    }
}
