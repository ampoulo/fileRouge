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
 * Les voitures du catalogue ont toutes le toit, le capot et le hayon de la même
 * couleur
 *
 * @author nokenn
 */
public class Constraint1 implements Constraint {

    private final Variable toit;
    private final Variable capot;
    private final Variable hayon;
    private final AllEqualConstraint all;
    private final Map<Variable, String> constrainte1;

    public Constraint1() {

        this.constrainte1 = new HashMap();

        this.toit = new Variable("toit", new ArrayList<>());
        this.capot = new Variable("capot", new ArrayList<>());
        this.hayon = new Variable("hayon", new ArrayList<>());

        this.constrainte1.put(toit, "");
        this.constrainte1.put(capot, "");
        this.constrainte1.put(hayon, "");

        this.all = new AllEqualConstraint(this.constrainte1);
    }

    @Override
    public Set<Variable> getScope() {

        return this.all.getScope();
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> choiclient) {

        return this.all.isSatisfiedBy(choiclient);
    }

    @Override
    public boolean filter(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
