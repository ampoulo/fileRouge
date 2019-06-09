/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppc;

import java.util.ArrayList;
import representations.Variable;

/**
 *
 * @author nokenn
 */
public class HeuristiqueOfDomaine implements Heuristique {

    public HeuristiqueOfDomaine() {

    }

    @Override
    public Variable getNextAssignation(ArrayList<Variable> nonAssigne) {
        
        int Size = 0;
        Variable choice = nonAssigne.get(0);

        for (int i = 0; i < nonAssigne.size(); i++) {

            if (nonAssigne.get(i).getDomaine2().size() > Size) {

                Size = nonAssigne.get(i).getDomaine2().size();
                choice = nonAssigne.get(i);
            }

        }

        return choice;
    }

}
