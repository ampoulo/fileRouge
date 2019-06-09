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
public interface Heuristique {

    public Variable getNextAssignation(ArrayList<Variable> nonAssigne);
}
