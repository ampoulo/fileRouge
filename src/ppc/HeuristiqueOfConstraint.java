/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppc;

import java.util.ArrayList;
import java.util.Set;
import representations.Constraint;
import representations.Variable;


/**
 *
 * @author nokenn
 */
public class HeuristiqueOfConstraint implements Heuristique {

    private final ArrayList<Constraint> constraint;

    public HeuristiqueOfConstraint(ArrayList<Constraint> constraint) {

        this.constraint = constraint;

    }

    private boolean inSetVariable(Set<Variable> scope, Variable v) {

        for (Variable lui : scope) {

            if (lui.equals(v)) {

                return true;
            }
        }

        return false;
    }

    private int participate(Variable v) {

        int nbr = 0;

        for (int i = 0; i < this.constraint.size(); i++) {

            if (this.inSetVariable(this.constraint.get(i).getScope(), v)) {

                nbr++;

            }

        }

        return nbr;
    }

    @Override
    public Variable getNextAssignation(ArrayList<Variable> nonAssigne) {

        int nbr = 0;
        int participe;
        Variable choice = nonAssigne.get(nbr);

        for (int i = 0; i < nonAssigne.size(); i++) {

            participe = participate(nonAssigne.get(i));

            if (participe > nbr) {

                choice = nonAssigne.get(i);
                nbr = participe;
            }

        }
        return choice;
    }

}
