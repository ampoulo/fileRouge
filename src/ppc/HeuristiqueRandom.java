/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppc;

import java.util.ArrayList;
import java.util.Random;
import representations.Variable;

/**
 *
 * @author nokenn
 */
public class HeuristiqueRandom implements Heuristique {

    Random r;

    public HeuristiqueRandom() {

        this.r = new Random();
    }

    @Override
    public Variable getNextAssignation(ArrayList<Variable> nonAssigne) {

        int Size = nonAssigne.size();
        return nonAssigne.get(r.nextInt(Size));
    }

}
