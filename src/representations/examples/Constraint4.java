/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import representations.*;

/**
 * Enfin, aucune voiture n'a à la fois un toit ouvrant et une sono
 *
 * @author nokenn
 */
public class Constraint4 implements Constraint {

    private final Variable sono;
    private final Variable toit_ouvrant;

    private final HashMap<Variable, String> constrainte4;
    private final HashMap<Variable, String> constrainte_4;

    private final IncompatibilityConstraint incomp0;
    private final IncompatibilityConstraint incomp1;

    public Constraint4() {

        this.sono = new Variable("sono", new ArrayList<>());
        this.toit_ouvrant = new Variable("toit_ouvrant", new ArrayList<>());

        this.constrainte4 = new HashMap();
        this.constrainte4.put(sono, "true");
        this.constrainte4.put(toit_ouvrant, "false");
        incomp0 = new IncompatibilityConstraint(this.constrainte4);

        this.constrainte_4 = new HashMap();

        this.constrainte_4.put(sono, "false");
        this.constrainte_4.put(toit_ouvrant, "true");

        incomp1 = new IncompatibilityConstraint(this.constrainte_4);

    }

    @Override
    public Set<Variable> getScope() {
        Set<Variable> monScope = new HashSet();

        monScope.addAll(this.constrainte4.keySet());
        monScope.addAll(this.constrainte_4.keySet());

        return monScope;

    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> choiclient) {

        return !this.incomp0.isSatisfiedBy(choiclient) || !this.incomp1.isSatisfiedBy(choiclient);

    }

    @Override
    public boolean filter(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
