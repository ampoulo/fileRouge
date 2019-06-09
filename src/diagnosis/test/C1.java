/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosis.test;

import java.util.*;
import representations.*;
/**
 * Reprensetation de la contriante du cour x1 && x2 implique x3
 * @author nokenn
 */
public class C1 implements Constraint {

    private final Variable x1;
    private final Variable x2;
    private final Variable x3;
    
    private final Map<Variable, String> constrainte1;
    private final Map<Variable, String> constrainte2;
    private final IncompatibilityConstraint incomp;

    private final Disjunction disjunction;

    public C1() {

        x1 = new Variable("x1", new ArrayList<>());
        x2 = new Variable("x2", new ArrayList<>());
        x3 = new Variable("x3", new ArrayList<>());

        this.constrainte1 = new HashMap();

        this.constrainte1.put(x1, "true");
        this.constrainte1.put(x2, "true");

        this.incomp = new IncompatibilityConstraint(this.constrainte1);

        this.constrainte2 = new HashMap();

        this.constrainte2.put(x3, "true");
        
        this.disjunction = new Disjunction(this.constrainte2);

    }

    @Override
    public Set<Variable> getScope() {

        Set<Variable> monScope = new HashSet();
        monScope.addAll(this.incomp.getScope());
        monScope.addAll(this.disjunction.getScope());
        return monScope;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> choiclient) {
        
        return this.incomp.isSatisfiedBy(choiclient) || this.disjunction.isSatisfiedBy(choiclient);
    }

    @Override
    public boolean filter(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {

        return this.incomp.filter(assigne, nonAssigne);
    }

    

}
