/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosis.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import representations.*;

/**
 * Representation de la contrainte de l'exemple simple du cour non x3 implique
 * x4
 *
 * @author nokenn
 */
public class C2 implements Constraint {

    private final Variable x4;
    private final Variable x3;
    private final Map<Variable, String> constrainte1;
    private final Disjunction disj;

    public C2() {

        x3 = new Variable("x3",new ArrayList<>());
        x4 = new Variable("x4", new ArrayList<>());

        this.constrainte1 = new HashMap();

        this.constrainte1.put(x3, "true");
        this.constrainte1.put(x4, "true");

        this.disj = new Disjunction(this.constrainte1);

    }

    @Override
    public Set<Variable> getScope() {

        return this.disj.getScope();
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> choiclient) {

        return this.disj.isSatisfiedBy(choiclient);
    }

    @Override
    public boolean filter(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
